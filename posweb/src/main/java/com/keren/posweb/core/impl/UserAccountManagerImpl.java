
package com.keren.posweb.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.posweb.core.ifaces.UserAccountManagerLocal;
import com.keren.posweb.core.ifaces.UserAccountManagerRemote;
import com.keren.posweb.dao.ifaces.UserAccountDAOLocal;
import com.keren.posweb.model.UserAccount;

@TransactionAttribute
@Stateless(mappedName = "UserAccountManager")
public class UserAccountManagerImpl
    extends AbstractGenericManager<UserAccount, Long>
    implements UserAccountManagerLocal, UserAccountManagerRemote
{

    @EJB(name = "UserAccountDAO")
    protected UserAccountDAOLocal dao;

    public UserAccountManagerImpl() {
    }

    @Override
    public GenericDAO<UserAccount, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
