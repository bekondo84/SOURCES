
package com.keren.dao.ifaces.employes;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.model.employes.Fonction;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Feb 14 12:53:09 GMT+01:00 2018
 * 
 */
public interface FonctionDAO
    extends GenericDAO<Fonction, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "FonctionDAO";

}
