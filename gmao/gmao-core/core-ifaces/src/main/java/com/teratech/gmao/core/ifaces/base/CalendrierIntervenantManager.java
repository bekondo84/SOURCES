
package com.teratech.gmao.core.ifaces.base;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.gmao.model.base.CalendrierIntervenant;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Sat Jul 14 13:40:51 GMT+01:00 2018
 * 
 */
public interface CalendrierIntervenantManager
    extends GenericManager<CalendrierIntervenant, Long>
{

    public final static String SERVICE_NAME = "CalendrierIntervenantManager";

}
