
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SMSINManagerLocal;
import com.keren.smsgateway.core.ifaces.SMSINManagerRemote;
import com.keren.smsgateway.dao.ifaces.SMSINDAOLocal;
import com.keren.smsgateway.model.SMSIN;

@TransactionAttribute
@Stateless(mappedName = "SMSINManager")
public class SMSINManagerImpl
    extends AbstractGenericManager<SMSIN, Long>
    implements SMSINManagerLocal, SMSINManagerRemote
{

    @EJB(name = "SMSINDAO")
    protected SMSINDAOLocal dao;

    public SMSINManagerImpl() {
    }

    @Override
    public GenericDAO<SMSIN, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
