
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.SMSCALLDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSCALLDAORemote;
import com.keren.smsgateway.model.SMSCALL;

@Stateless(mappedName = "SMSCALLDAO")
public class SMSCALLDAOImpl
    extends AbstractGenericDAO<SMSCALL, Long>
    implements SMSCALLDAOLocal, SMSCALLDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public SMSCALLDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SMSCALL> getManagedEntityClass() {
        return (SMSCALL.class);
    }

}
