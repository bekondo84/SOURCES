
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SMSConfigurationManagerLocal;
import com.keren.smsgateway.core.ifaces.SMSConfigurationManagerRemote;
import com.keren.smsgateway.dao.ifaces.SMSConfigurationDAOLocal;
import com.keren.smsgateway.model.SMSConfiguration;
import com.keren.smsgateway.model.SMSGateway;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "SMSConfigurationManager")
public class SMSConfigurationManagerImpl
    extends AbstractGenericManager<SMSConfiguration, Long>
    implements SMSConfigurationManagerLocal, SMSConfigurationManagerRemote
{

    @EJB(name = "SMSConfigurationDAO")
    protected SMSConfigurationDAOLocal dao;

    public SMSConfigurationManagerImpl() {
    }

    @Override
    public GenericDAO<SMSConfiguration, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    
    

    @Override
    public List<SMSConfiguration> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<SMSConfiguration> datas = dao.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<SMSConfiguration> result = new ArrayList<SMSConfiguration>();
        for(SMSConfiguration data : datas){
            result.add(new SMSConfiguration(data));
        }
        return result;
    }

    @Override
    public List<SMSConfiguration> findAll() {
        RestrictionsContainer container = RestrictionsContainer.newInstance();
         List<SMSConfiguration> datas = dao.filter(container.getPredicats(), null, null, 0, -1); //To change body of generated methods, choose Tools | Templates.
        List<SMSConfiguration> result = new ArrayList<SMSConfiguration>();
        for(SMSConfiguration data : datas){
            result.add(new SMSConfiguration(data));
        }
        return result;
    }
    
    

    @Override
    public SMSConfiguration find(String propertyName, Long entityID) {
        SMSConfiguration data = dao.findByPrimaryKey(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        SMSConfiguration result = new SMSConfiguration(data);
        for(SMSGateway gate:data.getModems()){
            result.getModems().add(new SMSGateway(gate));
        }
        return result;
    }
    
    

}
