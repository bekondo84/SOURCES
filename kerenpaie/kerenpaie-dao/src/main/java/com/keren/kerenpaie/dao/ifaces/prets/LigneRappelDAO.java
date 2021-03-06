
package com.keren.kerenpaie.dao.ifaces.prets;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.kerenpaie.model.prets.LigneRappel;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Mar 13 13:15:46 GMT+01:00 2018
 * 
 */
public interface LigneRappelDAO
    extends GenericDAO<LigneRappel, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "LigneRappelDAO";

}
