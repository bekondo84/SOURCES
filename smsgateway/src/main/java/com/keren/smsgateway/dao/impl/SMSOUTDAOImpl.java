
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SMSOUTDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSOUTDAORemote;
import com.keren.smsgateway.model.SMSOUT;

@Stateless(mappedName = "SMSOUTDAO")
public class SMSOUTDAOImpl
    extends AbstractGenericDAO<SMSOUT, Long>
    implements SMSOUTDAOLocal, SMSOUTDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SMSOUTDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SMSOUT> getManagedEntityClass() {
        return (SMSOUT.class);
    }

}
