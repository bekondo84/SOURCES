
package com.keren.smsgateway.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.smsgateway.dao.ifaces.ContactDAOLocal;
import com.keren.smsgateway.dao.ifaces.ContactDAORemote;
import com.keren.smsgateway.model.Contact;

@Stateless(mappedName = "ContactDAO")
public class ContactDAOImpl
    extends AbstractGenericDAO<Contact, Long>
    implements ContactDAOLocal, ContactDAORemote
{

    @PersistenceContext(unitName = "smsgateway")
    protected EntityManager em;

    public ContactDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Contact> getManagedEntityClass() {
        return (Contact.class);
    }

}
