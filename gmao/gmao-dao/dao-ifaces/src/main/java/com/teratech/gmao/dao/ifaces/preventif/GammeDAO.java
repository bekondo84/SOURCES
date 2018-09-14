
package com.teratech.gmao.dao.ifaces.preventif;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.gmao.model.preventif.Gamme;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Mon Jul 16 09:45:19 GMT+01:00 2018
 * 
 */
public interface GammeDAO
    extends GenericDAO<Gamme, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "GammeDAO";

}
