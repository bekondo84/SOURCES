
package com.keren.smsgateway.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.smsgateway.model.Contact;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 01 09:26:21 WAT 2019
 * 
 */
public interface ContactDAO
    extends GenericDAO<Contact, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ContactDAO";

}
