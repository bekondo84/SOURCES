
package com.keren.core.ifaces.comptabilite;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.model.comptabilite.Compte;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Wed Feb 14 12:53:09 GMT+01:00 2018
 * 
 */
public interface CompteManager
    extends GenericManager<Compte, Long>
{

    public final static String SERVICE_NAME = "CompteManager";

}
