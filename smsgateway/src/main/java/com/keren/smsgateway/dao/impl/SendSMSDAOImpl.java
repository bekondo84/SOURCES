
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SendSMSDAOLocal;
import com.keren.smsgateway.dao.ifaces.SendSMSDAORemote;
import com.keren.smsgateway.model.SendSMS;

@Stateless(mappedName = "SendSMSDAO")
public class SendSMSDAOImpl
    extends AbstractGenericDAO<SendSMS, Long>
    implements SendSMSDAOLocal, SendSMSDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SendSMSDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SendSMS> getManagedEntityClass() {
        return (SendSMS.class);
    }

}
