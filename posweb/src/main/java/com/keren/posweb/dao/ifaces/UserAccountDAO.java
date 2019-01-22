
package com.keren.posweb.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.posweb.model.UserAccount;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Mon Jan 21 15:03:47 WAT 2019
 * 
 */
public interface UserAccountDAO
    extends GenericDAO<UserAccount, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "UserAccountDAO";

}
