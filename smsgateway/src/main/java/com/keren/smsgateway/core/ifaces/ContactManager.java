
package com.keren.smsgateway.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.model.Contact;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 01 09:26:22 WAT 2019
 * 
 */
public interface ContactManager
    extends GenericManager<Contact, Long>
{

    public final static String SERVICE_NAME = "ContactManager";

}
