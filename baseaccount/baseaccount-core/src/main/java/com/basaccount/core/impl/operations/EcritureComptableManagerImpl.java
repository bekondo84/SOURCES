
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.EcritureComptableManagerLocal;
import com.basaccount.core.ifaces.operations.EcritureComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.CompteDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.Compte;
import com.basaccount.model.comptabilite.SectionAnalytique;
import com.basaccount.model.operations.EcritureAnalytique;
import com.basaccount.model.operations.EcritureComptable;
import com.basaccount.model.operations.EcritureTier;
import com.basaccount.model.search.EcritureSearch;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "EcritureComptableManager")
public class EcritureComptableManagerImpl
    extends AbstractGenericManager<EcritureComptable, Long>
    implements EcritureComptableManagerLocal, EcritureComptableManagerRemote
{

    @EJB(name = "EcritureComptableDAO")
    protected EcritureComptableDAOLocal dao;
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;

    @EJB(name = "CompteDAO")
    protected CompteDAOLocal comptedao;
    
    public EcritureComptableManagerImpl() {
    }

    @Override
    public GenericDAO<EcritureComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<EcritureComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<EcritureComptable> datas = dao.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<EcritureComptable> result = new ArrayList<EcritureComptable>();
        for(EcritureComptable ecrit:datas){
            result.add(new EcritureComptable(ecrit));
        }
        return result;
    }

    @Override
    public List<EcritureComptable> findAll() {
        //To change body of generated methods, choose Tools | Templates.
        List<EcritureComptable> datas =  super.findAll(); 
        List<EcritureComptable> result = new ArrayList<EcritureComptable>();
        for(EcritureComptable ecrit:datas){
            result.add(new EcritureComptable(ecrit));
        }
        return result;
    }

    @Override
    public EcritureComptable find(String propertyName, Long entityID) {
        //To change body of generated methods, choose Tools | Templates.
        EcritureComptable data = super.find(propertyName, entityID); 
        return new EcritureComptable(data);
    }

    @Override
    public EcritureComptable delete(Long id) {
        EcritureComptable ecriture = dao.findByPrimaryKey("id", id); //To change body of generated methods, choose Tools | Templates.
        return new EcritureComptable(ecriture);
    }

    @Override
    public void processBeforeSave(EcritureComptable entity) {
          entity.setId(-1L);        
    }
    
    
    @Override
    public List<EcritureComptable> somethings(EcritureSearch critere) {
         //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        if(critere!=null){
            if(critere.getSource()!=null){
                container.addGe("compte", critere.getSource());
            }
            if(critere.getCible()!=null){
                container.addLe("compte", critere.getCible());
            }
            if(critere.getDebut()!=null){
                container.addGe("dateEcriture", critere.getDebut());
            }
            if(critere.getFin()!=null){
                container.addLe("dateEcriture", critere.getFin());
            }
        }
        List<EcritureComptable> datas = dao.filter(container.getPredicats(), null, new HashSet<String>(), 0, -1);
        List<EcritureComptable>  result = new ArrayList<EcritureComptable>();
        for(EcritureComptable ecrit:datas){            
            EcritureComptable ecriture = new EcritureComptable(ecrit);
            result.add(ecriture);
        }
        return result;
    }
}
