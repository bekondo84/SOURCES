
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.OperationBancaireManagerLocal;
import com.basaccount.core.ifaces.operations.OperationBancaireManagerRemote;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.OperationBancaireDAOLocal;
import com.basaccount.model.operations.EcritureBanque;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.OperationBancaire;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "OperationBancaireManager")
public class OperationBancaireManagerImpl
    extends AbstractGenericManager<OperationBancaire, Long>
    implements OperationBancaireManagerLocal, OperationBancaireManagerRemote
{

    @EJB(name = "OperationBancaireDAO")
    protected OperationBancaireDAOLocal dao;
     
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;    
    
    public OperationBancaireManagerImpl() {
    }

    @Override
    public GenericDAO<OperationBancaire, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<OperationBancaire> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<OperationBancaire> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<OperationBancaire> entities = new ArrayList<OperationBancaire>();
        for(OperationBancaire data:datas){
            entities.add(new OperationBancaire(data));
        }
        return entities;
    }

    @Override
    public OperationBancaire find(String propertyName, Long entityID) {
        OperationBancaire data =  super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        OperationBancaire entity = new OperationBancaire(data);
        for(EcritureBanque ecriture:data.getEcritures()){
            entity.getEcritures().add(new EcritureBanque(ecriture));
        }
        return entity;
    }

    @Override
    public OperationBancaire delete(Long id) {
        OperationBancaire data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        OperationBancaire entity = new OperationBancaire(data);
        return entity;
    }

    @Override
    public void processBeforeUpdate(OperationBancaire entity) {
        Double debit = 0.0;
        Double credit = 0.0;
        for(EcritureBanque ecriture:entity.getEcritures()){            
            if(ecriture.getId()<=0){
                ecriture.setId(-1L);
//                ecriture.setPeriode(entity.getPeriode());
//                ecriture.setJournal(entity.getJournal());                
            }//end if(ecriture.getId()<=0){
            debit +=ecriture.getDebit();
            credit+=ecriture.getCredit();
        }//end for(EcritureBanque ecriture:entity.getEcritures()){
        entity.setCredit(credit);
        entity.setDebit(debit);
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(OperationBancaire entity) {
        entity.setState("etabli");
         Double debit = 0.0;
        Double credit = 0.0;
        for(EcritureBanque ecriture:entity.getEcritures()){
//            ecriture.setPeriode(entity.getPeriode());
//            ecriture.setJournal(entity.getJournal());
            if(ecriture.getId()<=0){
                ecriture.setId(-1L);
            }//end if(ecriture.getId()<=0){
            debit +=ecriture.getDebit();
            credit+=ecriture.getCredit();
        }//end for(EcritureBanque ecriture:entity.getEcritures()){
        entity.setCredit(credit);
        entity.setDebit(debit);
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     /**
     * Genere les ecritures analytiques
     * @param entity 
     */
    private void ecritureComptableeGenerator(OperationBancaire entity){
        for(EcritureBanque ligne:entity.getEcritures()){
            EcritureComptable ecriture = new EcritureComptable(entity, ligne);
            ecriture.setRefPiece(entity.getCode());
            ecrituredao.save(ecriture);
        }//end for(LignePieceComptable ligne:entity.getEcritures()){
    }//end private void ecritureAnalytiqueGenerator

    @Override
    public OperationBancaire valider(OperationBancaire entity) {
            entity.setState("valide");
            ecritureComptableeGenerator(entity);
            dao.update(entity.getId(), entity);
            return entity;
    }

}
