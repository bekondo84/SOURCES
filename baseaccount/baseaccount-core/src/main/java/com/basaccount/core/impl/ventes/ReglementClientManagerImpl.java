
package com.basaccount.core.impl.ventes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.ventes.ReglementClientManagerLocal;
import com.basaccount.core.ifaces.ventes.ReglementClientManagerRemote;
import com.basaccount.dao.ifaces.achats.EcheanceReglementDAOLocal;
import com.basaccount.dao.ifaces.ventes.ReglementClientDAOLocal;
import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.ventes.LigneReglementClient;
import com.basaccount.model.ventes.ReglementClient;
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
@Stateless(mappedName = "ReglementClientManager")
public class ReglementClientManagerImpl
    extends AbstractGenericManager<ReglementClient, Long>
    implements ReglementClientManagerLocal, ReglementClientManagerRemote
{

    @EJB(name = "ReglementClientDAO")
    protected ReglementClientDAOLocal dao;

    @EJB(name = "EcheanceReglementDAODAO")
    protected EcheanceReglementDAOLocal echeancedao;
    
    public ReglementClientManagerImpl() {
    }

    @Override
    public GenericDAO<ReglementClient, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<ReglementClient> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<ReglementClient> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<ReglementClient> output = new ArrayList<ReglementClient>();
        for(ReglementClient data:datas){
            output.add(new ReglementClient(data));
        }
        return output;
    }

    @Override
    public ReglementClient find(String propertyName, Long entityID) {
        ReglementClient data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        ReglementClient entity = new ReglementClient(data);
        for(LigneReglementClient ligne:data.getLignes()){
            entity.getLignes().add(new LigneReglementClient(ligne));
        }
        return entity;
    }

    @Override
    public ReglementClient delete(Long id) {
        ReglementClient data = dao.findByPrimaryKey("id", id); //To change body of generated methods, choose Tools | Templates.
        for(LigneReglementClient ligne:data.getLignes()){
             if(ligne.getMontant()!=null && ligne.getMontant()>0){
                EcheanceReglement echeance = echeancedao.findByPrimaryKey("id", ligne.getEcheaneid());
                echeance.addPercu(ligne.getMontant()*-1);
                echeance.setEtat(Boolean.FALSE);
                echeancedao.update(echeance.getId(), echeance);
            }//end if(ligne.getMontant()!=null && ligne.getMontant()>0){
        }//end for(LigneReglementClient ligne:data.getLignes()){
        dao.delete(id);
        return new ReglementClient(data);
    }

    @Override
    public void processBeforeUpdate(ReglementClient entity) {
        Map<Long,LigneReglementClient> map = new HashMap<Long,LigneReglementClient>();
        ReglementClient old = dao.findByPrimaryKey("id", entity.getId());
        for(LigneReglementClient ligne:old.getLignes()){
            map.put(ligne.getId(), ligne);
        }//end for(LigneReglementClient ligne:old.getLignes()){
        //Compute the new
        double totalpercu = 0.0;
        double totaldette = 0.0; 
        int index = 1;
        for(LigneReglementClient ligne:entity.getLignes()){
             if(ligne.getId()<=0){
                ligne.setId(-1);
                Date today = new Date();
                ligne.setCompareid(today.getTime()+index);
            }//end if(ligne.getId()<=0){
            index++;
            if(ligne.getMontant()!=null && ligne.getMontant()>0){
                EcheanceReglement echeance = echeancedao.findByPrimaryKey("id", ligne.getEcheaneid());
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
    public void processBeforeSave(ReglementClient entity) {
        double totalpercu = 0.0;
        double totaldette = 0.0;      
        int index = 1 ;
        for(LigneReglementClient ligne:entity.getLignes()){
            if(ligne.getId()<=0){
                ligne.setId(-1);
                Date today = new Date();
                ligne.setCompareid(today.getTime()+index);
            }//end if(ligne.getId()<=0){
            index++;
            if(ligne.getMontant()!=null && ligne.getMontant()>0){
                EcheanceReglement echeance = echeancedao.findByPrimaryKey("id", ligne.getEcheaneid());
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
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
