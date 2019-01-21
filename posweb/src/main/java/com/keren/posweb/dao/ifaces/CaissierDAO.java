
package com.keren.posweb.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.posweb.model.Caissier;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Mon Jan 21 13:47:29 WAT 2019
 * 
 */
public interface CaissierDAO
    extends GenericDAO<Caissier, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "CaissierDAO";

}
