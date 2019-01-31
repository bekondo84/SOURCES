
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SMSCALLManagerLocal;
import com.keren.smsgateway.core.ifaces.SMSCALLManagerRemote;
import com.keren.smsgateway.dao.ifaces.SMSCALLDAOLocal;
import com.keren.smsgateway.model.SMSCALL;

@TransactionAttribute
@Stateless(mappedName = "SMSCALLManager")
public class SMSCALLManagerImpl
    extends AbstractGenericManager<SMSCALL, Long>
    implements SMSCALLManagerLocal, SMSCALLManagerRemote
{

    @EJB(name = "SMSCALLDAO")
    protected SMSCALLDAOLocal dao;

    public SMSCALLManagerImpl() {
    }

    @Override
    public GenericDAO<SMSCALL, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
