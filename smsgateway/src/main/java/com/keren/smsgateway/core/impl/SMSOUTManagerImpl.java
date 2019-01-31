
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.SMSOUTManagerLocal;
import com.keren.smsgateway.core.ifaces.SMSOUTManagerRemote;
import com.keren.smsgateway.dao.ifaces.SMSOUTDAOLocal;
import com.keren.smsgateway.model.SMSOUT;

@TransactionAttribute
@Stateless(mappedName = "SMSOUTManager")
public class SMSOUTManagerImpl
    extends AbstractGenericManager<SMSOUT, Long>
    implements SMSOUTManagerLocal, SMSOUTManagerRemote
{

    @EJB(name = "SMSOUTDAO")
    protected SMSOUTDAOLocal dao;

    public SMSOUTManagerImpl() {
    }

    @Override
    public GenericDAO<SMSOUT, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
