
package com.keren.posweb.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.posweb.model.UserAccount;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Mon Jan 21 15:03:48 WAT 2019
 * 
 */
public interface UserAccountManager
    extends GenericManager<UserAccount, Long>
{

    public final static String SERVICE_NAME = "UserAccountManager";

}
