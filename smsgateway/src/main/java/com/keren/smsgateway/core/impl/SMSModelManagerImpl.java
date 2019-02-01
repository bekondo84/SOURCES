
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SMSModelManagerLocal;
import com.keren.smsgateway.core.ifaces.SMSModelManagerRemote;
import com.keren.smsgateway.dao.ifaces.SMSModelDAOLocal;
import com.keren.smsgateway.model.SMSModel;

@TransactionAttribute
@Stateless(mappedName = "SMSModelManager")
public class SMSModelManagerImpl
    extends AbstractGenericManager<SMSModel, Long>
    implements SMSModelManagerLocal, SMSModelManagerRemote
{

    @EJB(name = "SMSModelDAO")
    protected SMSModelDAOLocal dao;

    public SMSModelManagerImpl() {
    }

    @Override
    public GenericDAO<SMSModel, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
