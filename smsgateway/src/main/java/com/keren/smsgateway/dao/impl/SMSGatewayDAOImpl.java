
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SMSGatewayDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSGatewayDAORemote;
import com.keren.smsgateway.model.SMSGateway;

@Stateless(mappedName = "SMSGatewayDAO")
public class SMSGatewayDAOImpl
    extends AbstractGenericDAO<SMSGateway, Long>
    implements SMSGatewayDAOLocal, SMSGatewayDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SMSGatewayDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SMSGateway> getManagedEntityClass() {
        return (SMSGateway.class);
    }

}
