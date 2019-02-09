
package com.basaccount.core.ifaces.achats;

import com.basaccount.model.achats.EcheanceReglement;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 08 21:54:40 WAT 2019
 * 
 */
public interface EcheanceReglementManager
    extends GenericManager<EcheanceReglement, Long>
{

    public final static String SERVICE_NAME = "EcheanceReglementManager";

}
