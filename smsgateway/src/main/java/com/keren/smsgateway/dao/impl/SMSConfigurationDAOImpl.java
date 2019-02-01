
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SMSConfigurationDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSConfigurationDAORemote;
import com.keren.smsgateway.model.SMSConfiguration;

@Stateless(mappedName = "SMSConfigurationDAO")
public class SMSConfigurationDAOImpl
    extends AbstractGenericDAO<SMSConfiguration, Long>
    implements SMSConfigurationDAOLocal, SMSConfigurationDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SMSConfigurationDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SMSConfiguration> getManagedEntityClass() {
        return (SMSConfiguration.class);
    }

}
