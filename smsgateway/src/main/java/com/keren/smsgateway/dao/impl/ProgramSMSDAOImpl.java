
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.ProgramSMSDAOLocal;
import com.keren.smsgateway.dao.ifaces.ProgramSMSDAORemote;
import com.keren.smsgateway.model.ProgramSMS;

@Stateless(mappedName = "ProgramSMSDAO")
public class ProgramSMSDAOImpl
    extends AbstractGenericDAO<ProgramSMS, Long>
    implements ProgramSMSDAOLocal, ProgramSMSDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public ProgramSMSDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ProgramSMS> getManagedEntityClass() {
        return (ProgramSMS.class);
    }

}
