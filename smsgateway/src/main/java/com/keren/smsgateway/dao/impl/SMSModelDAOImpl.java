
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SMSModelDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSModelDAORemote;
import com.keren.smsgateway.model.SMSModel;

@Stateless(mappedName = "SMSModelDAO")
public class SMSModelDAOImpl
    extends AbstractGenericDAO<SMSModel, Long>
    implements SMSModelDAOLocal, SMSModelDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SMSModelDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SMSModel> getManagedEntityClass() {
        return (SMSModel.class);
    }

}
