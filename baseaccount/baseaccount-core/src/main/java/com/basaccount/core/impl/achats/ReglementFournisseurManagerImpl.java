
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.ReglementFournisseurManagerLocal;
import com.basaccount.core.ifaces.achats.ReglementFournisseurManagerRemote;
import com.basaccount.dao.ifaces.achats.EcheanceReglementDAOLocal;
import com.basaccount.dao.ifaces.achats.ReglementFournisseurDAOLocal;
import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.achats.LigneReglementFournisseur;
import com.basaccount.model.achats.ReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "ReglementFournisseurManager")
public class ReglementFournisseurManagerImpl
    extends AbstractGenericManager<ReglementFournisseur, Long>
    implements ReglementFournisseurManagerLocal, ReglementFournisseurManagerRemote
{

    @EJB(name = "ReglementFournisseurDAO")
    protected ReglementFournisseurDAOLocal dao;

    @EJB(name = "EcheanceReglementDAODAO")
    protected EcheanceReglementDAOLocal echeancedao;
    
    public ReglementFournisseurManagerImpl() {
    }

    @Override
    public GenericDAO<ReglementFournisseur, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<ReglementFournisseur> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<ReglementFournisseur> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<ReglementFournisseur> result = new ArrayList<ReglementFournisseur>();
        for(ReglementFournisseur data:datas){
            result.add(new ReglementFournisseur(data));
        }
        return result;
    }

    @Override
    public List<ReglementFournisseur> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReglementFournisseur find(String propertyName, Long entityID) {
        ReglementFournisseur data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        ReglementFournisseur result = new ReglementFournisseur(data);
        for(LigneReglementFournisseur ligne:data.getLignes()){
            result.getLignes().add(new LigneReglementFournisseur(ligne));
        }
        return result;
    }

    @Override
    public ReglementFournisseur delete(Long id) {
         ReglementFournisseur data = dao.findByPrimaryKey("id", id); //To change body of generated methods, choose Tools | Templates.
        for(LigneReglementFournisseur ligne:data.getLignes()){
             if(ligne.getMontant()!=null && ligne.getMontant()>0){
                EcheanceReglement echeance = echeancedao.findByPrimaryKey("id", ligne.getEcheanceid());
                echeance.addPercu(ligne.getMontant()*-1);
                echeance.setEtat(Boolean.FALSE);
                echeancedao.update(echeance.getId(), echeance);
            }//end if(ligne.getMontant()!=null && ligne.getMontant()>0){
        }//end for(LigneReglementClient ligne:data.getLignes()){
        dao.delete(id);
        return new ReglementFournisseur(data);
    }

    @Override
    public void processBeforeUpdate(ReglementFournisseur entity) {
        Map<Long,LigneReglementFournisseur> map = new HashMap<Long,LigneReglementFournisseur>();
        ReglementFournisseur old = dao.findByPrimaryKey("id", entity.getId());
        for(LigneReglementFournisseur ligne:old.getLignes()){
            map.put(ligne.getId(), ligne);
        }//end for(LigneReglementClient ligne:old.getLignes()){
        //Compute the new
        double totalpercu = 0.0;
        double totaldette = 0.0; 
        int index = 1;
        for(LigneReglementFournisseur ligne:entity.getLignes()){
             if(ligne.getId()<=0){
                ligne.setId(-1);
                 Date today = new Date();
                ligne.setCompareid(today.getTime()+index);
            }//end if(ligne.getId()<=0){
            index++;
            if(ligne.getMontant()!=null && ligne.getMontant()>0){
                EcheanceReglement echeance = echeancedao.findByPrimaryKey("id", ligne.getEcheanceid());
                double montant = ligne.getMontant()-(map.containsKey(ligne.getId()) ? map.get(ligne.getId()).getMontant() : 0.0);
                echeance.addPercu(montant);
                echeance.setEtat(echeance.getSolde()<=0  ? Boolean.TRUE:Boolean.FALSE);
                echeancedao.update(echeance.getId(), echeance);
                totalpercu+=ligne.getMontant();
            }//end if(ligne.getMontant()!=null && ligne.getMontant()>0){
            totaldette+=ligne.getSolde();
        }//end for(LigneReglementClient ligne:entity.getLignes()){
        entity.setTotalattendu(totaldette);
        entity.setTotalrecu(totalpercu);
        entity.setSolde(totaldette-totalpercu);
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(ReglementFournisseur entity) {
        double totalpercu = 0.0;
        double totaldette = 0.0;      
        int index = 1 ;
        for(LigneReglementFournisseur ligne:entity.getLignes()){
            if(ligne.getId()<=0){
                ligne.setId(-1);
                Date today = new Date();
                ligne.setCompareid(today.getTime()+index);
            }//end if(ligne.getId()<=0){
            index++;
            if(ligne.getMontant()!=null && ligne.getMontant()>0){
                EcheanceReglement echeance = echeancedao.findByPrimaryKey("id", ligne.getEcheanceid());
                echeance.addPercu(ligne.getMontant());
                echeance.setEtat(echeance.getSolde()<=0  ? Boolean.TRUE:Boolean.FALSE);
                echeancedao.update(echeance.getId(), echeance);
                totalpercu+=ligne.getMontant();
            }//end if(ligne.getMontant()!=null && ligne.getMontant()>0){
            totaldette+=ligne.getSolde();
        }//end for(LigneReglementClient ligne:entity.getLignes()){
        entity.setTotalattendu(totaldette);
        entity.setTotalrecu(totalpercu);
        entity.setSolde(totaldette-totalpercu);
        entity.setState("etabli");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
}
