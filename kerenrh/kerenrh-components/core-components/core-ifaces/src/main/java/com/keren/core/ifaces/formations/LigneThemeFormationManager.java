
package com.keren.core.ifaces.formations;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.model.formations.LigneThemeFormation;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Apr 10 13:14:14 GMT+01:00 2018
 * 
 */
public interface LigneThemeFormationManager
    extends GenericManager<LigneThemeFormation, Long>
{

    public final static String SERVICE_NAME = "LigneThemeFormationManager";

}
