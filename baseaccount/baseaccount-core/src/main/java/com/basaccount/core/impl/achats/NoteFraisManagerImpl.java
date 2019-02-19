
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.NoteFraisManagerLocal;
import com.basaccount.core.ifaces.achats.NoteFraisManagerRemote;
import com.basaccount.dao.ifaces.achats.NoteFraisDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.achats.LigneNoteFrais;
import com.basaccount.model.achats.NoteFrais;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.comptabilite.Taxe;
import com.basaccount.model.operations.EcritureComptable;
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
@Stateless(mappedName = "NoteFraisManager")
public class NoteFraisManagerImpl
    extends AbstractGenericManager<NoteFrais, Long>
    implements NoteFraisManagerLocal, NoteFraisManagerRemote
{

    @EJB(name = "NoteFraisDAO")
    protected NoteFraisDAOLocal dao;    
    
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;

    public NoteFraisManagerImpl() {
    }

    @Override
    public GenericDAO<NoteFrais, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<NoteFrais> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<NoteFrais> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<NoteFrais> result = new ArrayList<NoteFrais>();
        for(NoteFrais data:datas){
            result.add(new NoteFrais(data));
        }
        return result;
    }

    @Override
    public NoteFrais find(String propertyName, Long entityID) {
        NoteFrais data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        NoteFrais entity = new NoteFrais(data);     
        for(LigneNoteFrais ligne:data.getNotes()){
            LigneNoteFrais ligne2 = new LigneNoteFrais(ligne);
            for(Taxe tax:ligne.getTaxes()){
                ligne2.getTaxes().add(new Taxe(tax));
            }//endfor(Taxe tax:ligne.getTaxes()){
            entity.getNotes().add(ligne2);
        }
         for(EcheanceReglement ec:data.getEcheances()){
            entity.getEcheances().add(new EcheanceReglement(ec));
        }//end for(EcheanceReglement ec:data.getEcheances()){
        return entity;
    }

    @Override
    public NoteFrais delete(Long id) {
        NoteFrais entity = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new NoteFrais(entity);
    }

    @Override
    public void processBeforeUpdate(NoteFrais entity) {
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
    public void processBeforeSave(NoteFrais entity) {
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
        entity.setState("etabli");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NoteFrais valider(NoteFrais entity, PeriodeComptable periode) {
        //To change body of generated methods, choose Tools | Templates.
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
        EcritureComptable ecrittier = new EcritureComptable(entity.getDate(), entity.getCode(), entity.getFournisseur().getCompte().getLibele(), periode, entity.getJournal(), entity.getFournisseur().getCompte(), entity.getFournisseur(), 0.0, entity.getTotalttc());
        ecrituredao.save(ecrittier);
        EcritureComptable ecritvte = new EcritureComptable(entity.getDate(), entity.getCode(), entity.getMemo(), periode, entity.getJournal(), entity.getComptecompens(), null, entity.getTotalht(), 0.0);
        ecrituredao.save(ecritvte);
        //Ecriture taxes
        for(Compte compte : map.keySet()){
            EcritureComptable ecriture = new EcritureComptable(entity.getDate(), entity.getCode(), compte.getLibele(), periode, entity.getJournal(), compte, null, map.get(compte), 0.0);
            ecrituredao.save(ecriture);
        }//end for(Compte compte : map.keySet()){
        entity.setState("valide");
        dao.update(entity.getId(), entity);
        return entity;
    }

    
    
}
