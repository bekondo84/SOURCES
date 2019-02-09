
package com.basaccount.core.ifaces.ventes;

import com.basaccount.model.ventes.LigneReglementClient;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 08 21:59:57 WAT 2019
 * 
 */
public interface LigneReglementClientManager
    extends GenericManager<LigneReglementClient, Long>
{

    public final static String SERVICE_NAME = "LigneReglementClientManager";

}
