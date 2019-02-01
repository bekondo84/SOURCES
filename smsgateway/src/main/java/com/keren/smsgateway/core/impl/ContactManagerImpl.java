
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.ContactManagerLocal;
import com.keren.smsgateway.core.ifaces.ContactManagerRemote;
import com.keren.smsgateway.dao.ifaces.ContactDAOLocal;
import com.keren.smsgateway.model.Contact;

@TransactionAttribute
@Stateless(mappedName = "ContactManager")
public class ContactManagerImpl
    extends AbstractGenericManager<Contact, Long>
    implements ContactManagerLocal, ContactManagerRemote
{

    @EJB(name = "ContactDAO")
    protected ContactDAOLocal dao;

    public ContactManagerImpl() {
    }

    @Override
    public GenericDAO<Contact, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
