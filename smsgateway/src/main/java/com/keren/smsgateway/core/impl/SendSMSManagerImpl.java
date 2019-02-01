
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SendSMSManagerLocal;
import com.keren.smsgateway.core.ifaces.SendSMSManagerRemote;
import com.keren.smsgateway.dao.ifaces.SendSMSDAOLocal;
import com.keren.smsgateway.model.SendSMS;

@TransactionAttribute
@Stateless(mappedName = "SendSMSManager")
public class SendSMSManagerImpl
    extends AbstractGenericManager<SendSMS, Long>
    implements SendSMSManagerLocal, SendSMSManagerRemote
{

    @EJB(name = "SendSMSDAO")
    protected SendSMSDAOLocal dao;

    public SendSMSManagerImpl() {
    }

    @Override
    public GenericDAO<SendSMS, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
