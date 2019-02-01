
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.GroupeContactDAOLocal;
import com.keren.smsgateway.dao.ifaces.GroupeContactDAORemote;
import com.keren.smsgateway.model.GroupeContact;

@Stateless(mappedName = "GroupeContactDAO")
public class GroupeContactDAOImpl
    extends AbstractGenericDAO<GroupeContact, Long>
    implements GroupeContactDAOLocal, GroupeContactDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public GroupeContactDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<GroupeContact> getManagedEntityClass() {
        return (GroupeContact.class);
    }

}
