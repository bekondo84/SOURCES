
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.JournalSaisieManagerLocal;
import com.basaccount.core.ifaces.operations.JournalSaisieManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.basaccount.model.operations.JournalSaisie;
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
@Stateless(mappedName = "JournalSaisieManager")
public class JournalSaisieManagerImpl
    extends AbstractGenericManager<JournalSaisie, Long>
    implements JournalSaisieManagerLocal, JournalSaisieManagerRemote
{

    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal dao;
    
    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal ecrituredao;    
    
    @EJB(name = "CompteDAO")
    protected CompteDAOLocal comptedao;
    

    public JournalSaisieManagerImpl() {
    }

    @Override
    public GenericDAO<JournalSaisie, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<JournalSaisie> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        //To change body of generated methods, choose Tools | Templates.
        List<JournalSaisie> datas = dao.filter(predicats, orders, properties, firstResult, maxResult); 
        List<JournalSaisie> result = new ArrayList<JournalSaisie>();
        for(JournalSaisie data:datas){
            result.add(new JournalSaisie(data));
        }
        return result;
    }

    @Override
    public List<JournalSaisie> findAll() {
        //To change body of generated methods, choose Tools | Templates.
        List<JournalSaisie> datas =  super.findAll(); 
        List<JournalSaisie> result = new ArrayList<JournalSaisie>();
        for(JournalSaisie data:datas){
            result.add(new JournalSaisie(data));
        }
        return result;
    }

   
    @Override
    public JournalSaisie find(String propertyName, Long id) {
        JournalSaisie data = super.find(propertyName, id); //To change body of generated methods, choose Tools | Templates.
        JournalSaisie result = new JournalSaisie(data);
        for(EcritureComptable ecrit:data.getOperations()){
            EcritureComptable ecriture = new EcritureComptable(ecrit);            
            result.getOperations().add(ecriture);
        }
        return result;
    }

    @Override
    public void processBeforeUpdate(JournalSaisie entity) {
        //Map contenant les ecritures 
       super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JournalSaisie update(Long id, JournalSaisie entity) {
        return new JournalSaisie(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public JournalSaisie delete(Long id) {
         //To change body of generated methods, choose Tools | Templates.
        return new JournalSaisie();
    }     

}
