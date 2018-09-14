
package com.kerenedu.personnel;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Wed Jan 31 17:41:19 CET 2018
 * 
 */
public interface JoursCoursManager
    extends GenericManager<JoursCours, Long>
{

    public final static String SERVICE_NAME = "JoursCoursManager";
   
}
