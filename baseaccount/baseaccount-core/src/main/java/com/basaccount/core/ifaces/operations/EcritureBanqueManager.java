
package com.basaccount.core.ifaces.operations;

import com.basaccount.model.operations.EcritureBanque;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Wed Jan 16 11:53:27 WAT 2019
 * 
 */
public interface EcritureBanqueManager
    extends GenericManager<EcritureBanque, Long>
{

    public final static String SERVICE_NAME = "EcritureBanqueManager";

}
