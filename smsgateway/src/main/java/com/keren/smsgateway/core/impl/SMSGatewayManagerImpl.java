
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SMSGatewayManagerLocal;
import com.keren.smsgateway.core.ifaces.SMSGatewayManagerRemote;
import com.keren.smsgateway.dao.ifaces.SMSGatewayDAOLocal;
import com.keren.smsgateway.model.SMSGateway;

@TransactionAttribute
@Stateless(mappedName = "SMSGatewayManager")
public class SMSGatewayManagerImpl
    extends AbstractGenericManager<SMSGateway, Long>
    implements SMSGatewayManagerLocal, SMSGatewayManagerRemote
{

    @EJB(name = "SMSGatewayDAO")
    protected SMSGatewayDAOLocal dao;

    public SMSGatewayManagerImpl() {
    }

    @Override
    public GenericDAO<SMSGateway, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
