
package com.keren.posweb.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.posweb.model.Taxe;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Thu Sep 06 10:32:17 GMT+01:00 2018
 * 
 */
public interface TaxeManager
    extends GenericManager<Taxe, Long>
{

    public final static String SERVICE_NAME = "TaxeManager";

}
