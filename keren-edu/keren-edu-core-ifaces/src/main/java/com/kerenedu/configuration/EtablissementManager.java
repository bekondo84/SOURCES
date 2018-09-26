
package com.kerenedu.configuration;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Jan 09 15:21:42 WAT 2018
 * 
 */
public interface EtablissementManager
    extends GenericManager<Etablissement, Long>
{

    public final static String SERVICE_NAME = "EtablissementManager";

}