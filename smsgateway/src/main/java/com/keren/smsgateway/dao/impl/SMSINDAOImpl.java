
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SMSINDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSINDAORemote;
import com.keren.smsgateway.model.SMSIN;

@Stateless(mappedName = "SMSINDAO")
public class SMSINDAOImpl
    extends AbstractGenericDAO<SMSIN, Long>
    implements SMSINDAOLocal, SMSINDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SMSINDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SMSIN> getManagedEntityClass() {
        return (SMSIN.class);
    }

}
