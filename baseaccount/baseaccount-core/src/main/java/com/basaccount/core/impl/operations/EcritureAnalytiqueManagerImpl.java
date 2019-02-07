
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.EcritureAnalytiqueManagerLocal;
import com.basaccount.core.ifaces.operations.EcritureAnalytiqueManagerRemote;
import com.basaccount.dao.ifaces.operations.EcritureAnalytiqueDAOLocal;
import com.basaccount.model.operations.EcritureAnalytique;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "EcritureAnalytiqueManager")
public class EcritureAnalytiqueManagerImpl
    extends AbstractGenericManager<EcritureAnalytique, Long>
    implements EcritureAnalytiqueManagerLocal, EcritureAnalytiqueManagerRemote
{

    @EJB(name = "EcritureAnalytiqueDAO")
    protected EcritureAnalytiqueDAOLocal dao;

    public EcritureAnalytiqueManagerImpl() {
    }

    @Override
    public GenericDAO<EcritureAnalytique, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<EcritureAnalytique> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<EcritureAnalytique> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<EcritureAnalytique> result = new ArrayList<EcritureAnalytique>();
        for(EcritureAnalytique data:datas){
            result.add(new EcritureAnalytique(data));
        }
        return result;
    }

    @Override
    public EcritureAnalytique find(String propertyName, Long entityID) {
        EcritureAnalytique data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        return new EcritureAnalytique(data);
    }

    @Override
    public EcritureAnalytique delete(Long id) {
        return super.delete(id); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
