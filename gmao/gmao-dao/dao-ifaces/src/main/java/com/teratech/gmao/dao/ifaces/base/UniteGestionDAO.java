
package com.teratech.gmao.dao.ifaces.base;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.gmao.model.base.UniteGestion;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Jul 13 14:45:37 GMT+01:00 2018
 * 
 */
public interface UniteGestionDAO
    extends GenericDAO<UniteGestion, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "UniteGestionDAO";

}
