
package com.basaccount.core.ifaces.achats;

import com.basaccount.model.achats.ModeReglement;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 08 22:34:45 WAT 2019
 * 
 */
public interface ModeReglementManager
    extends GenericManager<ModeReglement, Long>
{

    public final static String SERVICE_NAME = "ModeReglementManager";

}
