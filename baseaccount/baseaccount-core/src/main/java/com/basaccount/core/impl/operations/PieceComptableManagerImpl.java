
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.PieceComptableManagerLocal;
import com.basaccount.core.ifaces.operations.PieceComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.dao.ifaces.operations.PieceComptableDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.basaccount.model.operations.LignePieceComptable;
import com.basaccount.model.operations.PieceComptable;
import com.basaccount.model.tiers.Tier;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        PieceComptable entity = new PieceComptable(piece);
         return entity;
    } 
    

    

    @Override
    public PieceComptable valider(PieceComptable entity) {
        //To change body of generated methods, choose Tools | Templates.
        entity.setState("valide");
        ecritureComptableeGenerator(entity);
        dao.update(entity.getId(), entity);
        return entity;
    }
    
     /**
     * Genere les ecritures analytiques
     * @param entity 
     */
    private void ecritureComptableeGenerator(PieceComptable entity){
        for(LignePieceComptable ligne:entity.getEcritures()){
            EcritureComptable ecriture = new EcritureComptable(entity, ligne);
            ecriture.setRefPiece(entity.getCode());
            ecrituredao.save(ecriture);
        }//end for(LignePieceComptable ligne:entity.getEcritures()){
    }//end private void ecritureAnalytiqueGenerator

   
}
