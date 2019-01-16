
package com.basaccount.dao.ifaces.operations;

import com.basaccount.model.operations.EcritureBanque;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Jan 16 11:53:27 WAT 2019
 * 
 */
public interface EcritureBanqueDAO
    extends GenericDAO<EcritureBanque, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "EcritureBanqueDAO";

}
