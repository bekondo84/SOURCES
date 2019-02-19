
package com.basaccount.core.impl.ventes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.ventes.NoteFraisVenteManagerLocal;
import com.basaccount.core.ifaces.ventes.NoteFraisVenteManagerRemote;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.ventes.NoteFraisVenteDAOLocal;
import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.achats.LigneNoteFrais;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.comptabilite.Taxe;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.ventes.LigneNoteFraisVente;
import com.basaccount.model.ventes.NoteFraisVente;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "NoteFraisVenteManager")
public class NoteFraisVenteManagerImpl
    extends AbstractGenericManager<NoteFraisVente, Long>
    implements NoteFraisVenteManagerLocal, NoteFraisVenteManagerRemote
{

    @EJB(name = "NoteFraisVenteDAO")
    protected NoteFraisVenteDAOLocal dao;
    
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;
    
    
    public NoteFraisVenteManagerImpl() {
    }

    @Override
    public GenericDAO<NoteFraisVente, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<NoteFraisVente> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<NoteFraisVente> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<NoteFraisVente> result = new ArrayList<NoteFraisVente>();
        for(NoteFraisVente data:datas){
            result.add(new NoteFraisVente(data));
        }
        return result;
    }

    @Override
    public NoteFraisVente find(String propertyName, Long entityID) {
        NoteFraisVente data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        NoteFraisVente result = new NoteFraisVente(data);
        for(LigneNoteFrais ligne:data.getNotes()){
            LigneNoteFrais ligne2 = new LigneNoteFrais(ligne);
            for(Taxe tax:ligne.getTaxes()){
                ligne2.getTaxes().add(new Taxe(tax));
            }//endfor(Taxe tax:ligne.getTaxes()){
            result.getNotes().add(ligne2);
        }//end for(LigneNoteFrais ligne:data.getNotes()){
        return result;
    }

    @Override
    public NoteFraisVente delete(Long id) {
        NoteFraisVente data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new NoteFraisVente(data);
    }
    
     @Override
    public void processBeforeUpdate(NoteFraisVente entity) {
         Double totalht = 0.0;
        Double taxes = 0.0;
        Double totalttc = 0.0;
        for(LigneNoteFrais ligne:entity.getNotes()){
            double ltaxe = 0.0;
            for(Taxe taxe : ligne.getTaxes()){
                ltaxe+=(taxe.getCalculTaxe().equalsIgnoreCase("0")? taxe.getMontant():(ligne.getMontant()*taxe.getMontant())/100);
            }//end for(Taxe taxe : ligne.getTaxes()){
            ligne.setTaxe(ltaxe);
            ligne.setTotal(ligne.getMontant()+ligne.getTaxe());
            totalht+= ligne.getMontant();
            taxes += ligne.getTaxe();
            totalttc+=ligne.getTotal();
            if(ligne.getId()<=0){
                ligne.setId(-1L);
            }//end if(ligne.getId()<=0){
        }//end for(LigneNoteFrais ligne:entity.getNotes()){
        entity.setTotalht(totalht);
        entity.setTotaltaxes(taxes);
        entity.setTotalttc(totalttc);
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(NoteFraisVente entity) {
        Double totalht = 0.0;
        Double taxes = 0.0;
        Double totalttc = 0.0;
        for(LigneNoteFrais ligne:entity.getNotes()){
            double ltaxe = 0.0;
            for(Taxe taxe : ligne.getTaxes()){
                ltaxe+=(taxe.getCalculTaxe().equalsIgnoreCase("0")? taxe.getMontant():(ligne.getMontant()*taxe.getMontant())/100);
            }//end for(Taxe taxe : ligne.getTaxes()){
            ligne.setTaxe(ltaxe);
            ligne.setTotal(ligne.getMontant()+ligne.getTaxe());
            totalht+= ligne.getMontant();
            taxes += ligne.getTaxe();
            totalttc+=ligne.getTotal();
            if(ligne.getId()<=0){
                ligne.setId(-1L);
            }//end if(ligne.getId()<=0){
        }//end for(LigneNoteFrais ligne:entity.getNotes()){
        computeNote(entity);
        entity.setTotalht(totalht);
        entity.setTotaltaxes(taxes);
        entity.setTotalttc(totalttc);
        entity.setState("etabli");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NoteFraisVente valider(NoteFraisVente entity,PeriodeComptable periode) {        
        //generation des ecritures comptables
        Map<Compte,Double> map = new HashMap<Compte, Double>();
        for(LigneNoteFrais ligne:entity.getNotes()){
            if(ligne.getTaxes()!=null && !ligne.getTaxes().isEmpty()){
                for(Taxe tax : ligne.getTaxes()){
                    double value = tax.getMontant();
                    if(tax.getCalculTaxe()!=null && tax.getCalculTaxe().trim().equalsIgnoreCase("1")){
                        value = ligne.getMontant()*tax.getMontant()/100;
                    }//end if(tax.getCalculTaxe()!=null && tax.getCalculTaxe().trim().equalsIgnoreCase("1")){
                    if(map.containsKey(tax.getCompte())){
                        map.put(tax.getCompte(), map.get(tax.getCompte())+value);
                    }else{
                        map.put(tax.getCompte(), value);
                    }//end if(map.containsKey(tax.getCompte())){
                }//end for(Taxe tax : ligne.getTaxes()){
            }//end if(ligne.getTaxes()!=null && !ligne.getTaxes().isEmpty()){
        }//end for(LigneNoteFrais ligne:entity.getNotes()){
        computeNote(entity);
        EcritureComptable ecrittier = new EcritureComptable(entity.getDate(), entity.getCode(), entity.getFournisseur().getCompte().getLibele(), periode, entity.getJournal(), entity.getFournisseur().getCompte(), entity.getFournisseur(), entity.getTotalttc(), 0.0);
        ecrituredao.save(ecrittier);
        EcritureComptable ecritvte = new EcritureComptable(entity.getDate(), entity.getCode(), entity.getMemo(), periode, entity.getJournal(), entity.getComptecompens(), null, 0.0, entity.getTotalht());
        ecrituredao.save(ecritvte);
        //Ecriture taxes
        for(Compte compte : map.keySet()){
            EcritureComptable ecriture = new EcritureComptable(entity.getDate(), entity.getCode(), compte.getLibele(), periode, entity.getJournal(), compte, null, 0.0, map.get(compte));
            ecrituredao.save(ecriture);
        }//end for(Compte compte : map.keySet()){
        entity.setState("valide");
        dao.update(entity.getId(), entity);
        return entity;
    }

    private void computeNote(NoteFraisVente entity){
        for(EcheanceReglement ech:entity.getEcheances()){
            if(ech.getId()<0){
                ech.setId(-1L);
            }
        }
    }
}
