
package com.keren.posweb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.posweb.dao.ifaces.UserAccountDAOLocal;
import com.keren.posweb.dao.ifaces.UserAccountDAORemote;
import com.keren.posweb.model.UserAccount;

@Stateless(mappedName = "UserAccountDAO")
public class UserAccountDAOImpl
    extends AbstractGenericDAO<UserAccount, Long>
    implements UserAccountDAOLocal, UserAccountDAORemote
{

    @PersistenceContext(unitName = "posweb")
    protected EntityManager em;

    public UserAccountDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<UserAccount> getManagedEntityClass() {
        return (UserAccount.class);
    }

}
