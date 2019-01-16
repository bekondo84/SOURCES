
package com.basaccount.core.ifaces.ventes;

import com.basaccount.model.ventes.ReglementClient;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Wed Jan 16 11:12:50 WAT 2019
 * 
 */
public interface ReglementClientManager
    extends GenericManager<ReglementClient, Long>
{

    public final static String SERVICE_NAME = "ReglementClientManager";

}
