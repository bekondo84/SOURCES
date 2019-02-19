
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.PieceComptableManagerLocal;
import com.basaccount.core.ifaces.operations.PieceComptableManagerRemote;
import com.basaccount.dao.ifaces.achats.FactureDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.PeriodeComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.dao.ifaces.operations.PieceComptableDAOLocal;
import com.basaccount.dao.ifaces.ventes.FactureVenteDAOLocal;
import com.basaccount.model.achats.Facture;
import com.basaccount.model.achats.LigneFacture;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.comptabilite.Taxe;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.LignePieceComptable;
import com.basaccount.model.operations.PieceComptable;
import com.basaccount.model.ventes.FactureVente;
import com.basaccount.model.ventes.LigneFactureVente;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "PieceComptableManager")
public class PieceComptableManagerImpl
    extends AbstractGenericManager<PieceComptable, Long>
    implements PieceComptableManagerLocal, PieceComptableManagerRemote
{

    @EJB(name = "PieceComptableDAO")
    protected PieceComptableDAOLocal dao;
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;
    
    @EJB(name = "CompteDAO")
    protected CompteDAOLocal comptedao;
    
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;    
    
    @EJB(name = "FactureVenteDAO")
    protected FactureVenteDAOLocal fvdao;  
    
    @EJB(name = "FactureDAO")
    protected FactureDAOLocal fadao;  
    
     @EJB(name = "PeriodeComptableDAO")
    protected PeriodeComptableDAOLocal periodedao;  
    
    public PieceComptableManagerImpl() {
    }

    @Override
    public GenericDAO<PieceComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<PieceComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<PieceComptable> datas = dao.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<PieceComptable> results = new ArrayList<PieceComptable>();
        for(PieceComptable piece: datas){
            results.add(new PieceComptable(piece));
        }
        return results;
    }

    @Override
    public List<PieceComptable> findAll() {
        List<PieceComptable> datas =  super.findAll(); //To change body of generated methods, choose Tools | Templates.
        List<PieceComptable> results = new ArrayList<PieceComptable>();
        for(PieceComptable piece: datas){
            results.add(new PieceComptable(piece));
        }
        return results;
    }

    @Override
    public PieceComptable find(String propertyName, Long entityID) {
        //To change body of generated methods, choose Tools | Templates.
        PieceComptable piece = super.find(propertyName, entityID); 
         PieceComptable entity = new PieceComptable(piece);
         entity.setFacturevente(piece.getFacturevente());
        if(piece.getEcritures()!=null){
            for(LignePieceComptable ecri:piece.getEcritures()){
                LignePieceComptable ecriture = new LignePieceComptable(ecri);                
                entity.getEcritures().add(ecriture);
            }//end for(EcritureComptable ecri:piece.getEcritures())
        }//end if(piece.getEcritures()!=null)
        return entity;
    }

    @Override
    public void processBeforeUpdate(PieceComptable entity) {
            Double debit =0.0;
            Double credit = 0.0;
            for(LignePieceComptable ecriture : entity.getEcritures()){
                if(ecriture.getId()<=0){
//                    ecriture.setPeriode(entity.getPeriode());
//                    ecriture.setJournal(entity.getJournal());
                    ecriture.setId(-1L);
                }//end if(ecriture.getId()<=0){
                debit +=ecriture.getDebit();
                credit+=ecriture.getCredit();
            }//end for(EcritureComptable ecriture : entity.getEcritures()){
            entity.setCredit(credit);
            entity.setDebit(debit);      
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void processBeforeSave(PieceComptable entity) {
        entity.setState("etabli");
        Double debit =0.0;
        Double credit = 0.0;
        for(LignePieceComptable ecriture : entity.getEcritures()){
//            ecriture.setPeriode(entity.getPeriode());
//            ecriture.setJournal(entity.getJournal());
            if(ecriture.getId()<=0){
                ecriture.setId(-1L);
            }//end if(ecriture.getId()<=0){
            debit +=ecriture.getDebit();
            credit+=ecriture.getCredit();
        }//end for(EcritureComptable ecriture : entity.getEcritures()){
        entity.setCredit(credit);
        entity.setDebit(debit);
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public PieceComptable delete(Long id) {
        PieceComptable piece = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        if(piece.getFacturevente()!=null){
            FactureVente facture = fvdao.findByPrimaryKey("id", piece.getFacturevente());
            facture.getLignes().size();
            facture.getAcomptes().size();
            facture.getEcheances().size();
            facture.setState("transfere");
            fvdao.update(facture.getId(), facture);
        }//end if(piece.getFacturevente()!=null){
        if(piece.getFactureachat()!=null){
            Facture facture = fadao.findByPrimaryKey("id", piece.getFactureachat());
            facture.getLignes().size();
            facture.getAcomptes().size();
            facture.getEcheances().size();
            facture.setState("transfere");
            fadao.update(facture.getId(), facture);
        }
        PieceComptable entity = new PieceComptable(piece);
         return entity;
    } 
    

    

    @Override
    public PieceComptable valider(PieceComptable entity) {
        //To change body of generated methods, choose Tools | Templates.
        entity.setState("valide");
        ecritureComptableeGenerator(entity);
        dao.update(entity.getId(), entity);
        if(entity.getFacturevente()!=null){
            FactureVente facture = fvdao.findByPrimaryKey("id", entity.getFacturevente());
            facture.getLignes().size();
            facture.getAcomptes().size();
            facture.getEcheances().size();
            facture.setState("comptabilise");
            fvdao.update(facture.getId(), facture);
        }//end if(entity.getFacturevente()!=null){
        if(entity.getFactureachat()!=null){
            Facture facture = fadao.findByPrimaryKey("id", entity.getFactureachat());
            facture.getLignes().size();
            facture.getAcomptes().size();
            facture.getEcheances().size();
            facture.setState("comptabilise");
            fadao.update(facture.getId(), facture);
        }//end if(entity.getFacturevente()!=null){
        return entity;
    }
    
     /**
     * Genere les ecritures analytiques
     * @param entity 
     */
    private void ecritureComptableeGenerator(PieceComptable entity){
        int index = 1 ;
        for(LignePieceComptable ligne:entity.getEcritures()){
            Date today = new Date();
            EcritureComptable ecriture = new EcritureComptable(entity, ligne);
            ecriture.setRefPiece(entity.getCode());
            ecriture.setCompareid(today.getTime()+index);
            index++;
            ecrituredao.save(ecriture);
        }//end for(LignePieceComptable ligne:entity.getEcritures()){
    }//end private void ecritureAnalytiqueGenerator

    @Override
    public List<PieceComptable> priseencompte(Long id,PeriodeComptable periode) {
        //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("facturevente", id);
        List<PieceComptable> pieces = dao.filter(container.getPredicats(), null, null, 0, -1);
        if(pieces==null||pieces.isEmpty()){
                FactureVente facture = fvdao.findByPrimaryKey("id", id);
                facture.getLignes().size();
                facture.getAcomptes().size();
                facture.getEcheances().size();
                Map<Compte,Double> map = new HashMap<Compte, Double>();
                for(LigneFactureVente ligne:facture.getLignes()){
                    if(ligne.getTaxes()!=null && !ligne.getTaxes().isEmpty()){
                        for(Taxe tax:ligne.getTaxes()){
                            double value = tax.getMontant();
                            if(tax.getCalculTaxe()!=null && tax.getCalculTaxe().equalsIgnoreCase("1")){
                                value = ligne.getQuantite()*ligne.getPuht();
                                if(ligne.getRemise()!=null){
                                    value = value*(100-ligne.getRemise())/100;
                                }//end if(ligne.getRemise()!=null){
                                value = value*tax.getMontant()/100;
                            }//end if(tax.getCalculTaxe()=="1"){                        
                            if(map.containsKey(tax.getCompte())){                        
                                map.put(tax.getCompte(),map.get(tax.getCompte())+value);
                            }else{
                                map.put(tax.getCompte(),value);
                            }//end if(map.containsKey(tax.getCompte())){
                        }//end for(Taxe tax:ligne.getTaxes()){
                    }//end if(ligne.getTaxes()!=null && !ligne.getTaxes().isEmpty()){
                }//end for(LigneFactureVente ligne:facture.getLignes()){
                PieceComptable piece = new PieceComptable(facture, map);
                piece.setCredit(facture.getTotalttc());
                piece.setDebit(facture.getTotalttc());
                Date today = new Date();
                piece.setCompareid(today.getTime());
                piece.setPeriode(periode);
                dao.save(piece);
                facture.setState("prisencompte");
                fvdao.update(facture.getId(), facture);
                container = RestrictionsContainer.newInstance();
                container.addEq("facturevente", id);
                pieces = dao.filter(container.getPredicats(), null, null, 0, -1);
        }//end if(pieces==null||pieces.isEmpty()){
        List<PieceComptable> output = new ArrayList<PieceComptable>();
        for(PieceComptable piec:pieces){
            output.add(new PieceComptable(piec));
        }
        return output;
    }

    @Override
    public List<PieceComptable> priseencompteachat(Long id, PeriodeComptable periode) {
          //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("factureachat", id);
        List<PieceComptable> pieces = dao.filter(container.getPredicats(), null, null, 0, -1);
        if(pieces==null||pieces.isEmpty()){
                Facture facture = fadao.findByPrimaryKey("id", id);
                facture.getLignes().size();
                facture.getAcomptes().size();
                facture.getEcheances().size();
                double totalht = 0.0;
                Map<Compte,Double> map = new HashMap<Compte, Double>();               
                for(LigneFacture ligne:facture.getLignes()){
                    totalht += ligne.getQuantite()*ligne.getPuht()*(100-(ligne.getRemise()!=null ? ligne.getRemise():0.0))/100;
                    if(ligne.getTaxes()!=null && !ligne.getTaxes().isEmpty()){
                        for(Taxe tax:ligne.getTaxes()){
                            double value = tax.getMontant();
                            if(tax.getCalculTaxe()!=null && tax.getCalculTaxe().equalsIgnoreCase("1")){
                                value = ligne.getQuantite()*ligne.getPuht();
                                if(ligne.getRemise()!=null){
                                    value = value*(100-ligne.getRemise())/100;
                                }//end if(ligne.getRemise()!=null){
                                value = value*tax.getMontant()/100;
                            }//end if(tax.getCalculTaxe()=="1"){   
                            
                            if(map.containsKey(tax.getCompte())){                        
                                map.put(tax.getCompte(),map.get(tax.getCompte())+value);
                            }else{
                                map.put(tax.getCompte(),value);
                            }//end if(map.containsKey(tax.getCompte())){
                        }//end for(Taxe tax:ligne.getTaxes()){
                    }//end if(ligne.getTaxes()!=null && !ligne.getTaxes().isEmpty()){
                }//end for(LigneFactureVente ligne:facture.getLignes()){
//                facture.setTotalht(totalht);
                PieceComptable piece = new PieceComptable(facture, map);
                piece.setCredit(facture.getTotalttc());
                piece.setDebit(facture.getTotalttc());
                Date today = new Date();
                piece.setCompareid(today.getTime());
                piece.setPeriode(periode);
                dao.save(piece);
                facture.setState("prisencompte");
                fadao.update(facture.getId(), facture);
                container = RestrictionsContainer.newInstance();
                container.addEq("factureachat", id);
                pieces = dao.filter(container.getPredicats(), null, null, 0, -1);
        }//end if(pieces==null||pieces.isEmpty()){
        List<PieceComptable> output = new ArrayList<PieceComptable>();
        for(PieceComptable piec:pieces){
            output.add(new PieceComptable(piec));
        }
        return output;
    }

   
}
