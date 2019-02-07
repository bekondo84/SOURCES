
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAORemote;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import java.math.BigDecimal;

@Stateless(mappedName = "EcritureComptableDAO")
public class EcritureComptableDAOImpl
    extends AbstractGenericDAO<EcritureComptable, Long>
    implements EcritureComptableDAOLocal, EcritureComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EcritureComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<EcritureComptable> getManagedEntityClass() {
        return (EcritureComptable.class);
    }

    @Override
    public void processBeforeSave(EcritureComptable entity) {
        //Generation des ecritures analytiques
        ecritureAnalytiqueGenerator(entity);
        //Generation des ecriture tier
        ecritureTierGenerator(entity);        
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
    /**
     * Genere les ecritures analytiques
     * @param entity 
     */
    private void ecritureAnalytiqueGenerator(EcritureComptable entity){
        Compte compte = em.find(Compte.class, entity.getCompte().getId());
        if(compte.getAnalytiques()!=null&&!compte.getAnalytiques().isEmpty()){
            for(SectionAnalytique sec:compte.getAnalytiques()){
                double debit = 0.0;
                double credit = 0.0;
                if(sec.getType().equalsIgnoreCase("0")){//pourcentage
                    debit = (entity.getDebit()!=null ? entity.getDebit().doubleValue():0)*sec.getQuantite()/100;
                    credit = (entity.getCredit()!=null ? entity.getCredit().doubleValue():0)*sec.getQuantite()/100;
                }else if(sec.getType().equalsIgnoreCase("1")){
                    debit = (entity.getDebit()!=null ? entity.getDebit().doubleValue():0)/entity.getAnalytiques().size();
                    credit = (entity.getCredit()!=null ? entity.getCredit().doubleValue():0)/entity.getAnalytiques().size();
                }else{
                    debit = (entity.getDebit()!=null ? sec.getQuantite():0);
                    credit = (entity.getCredit()!=null ? sec.getQuantite():0);
                }//end if(sec.getType().equalsIgnoreCase("0")){
                EcritureAnalytique ecriture = new EcritureAnalytique(entity, sec.getCompte(),debit,credit);
                entity.getAnalytiques().add(ecriture);
            }//end for(SectionAnalytique sec:compte.getAnalytiques())
        }//end if(compte.getAnalytiques()!=null&&!compte.getAnalytiques().isEmpty())
    }//end private void ecritureAnalytiqueGenerator

    /**
     * 
     * @param entity 
     */
    private void ecritureTierGenerator(EcritureComptable entity){
       if(entity.getTier()!=null){ 
            EcritureTier ecriture = new EcritureTier(entity);
            entity.setEcrituretier(ecriture);
       }//end if(entity.getTier()!=null)
    }

}
