
package com.megatimgroup.dao.ifaces.parametres;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.megatimgroup.model.parametres.Societe;


/**
 * Interface etendue par les interfaces locale et remote de la DAO

 * @since Tue May 02 14:26:30 WAT 2017
 * 
 */
public interface SocieteDAO
    extends GenericDAO<Societe, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SocieteDAO";

}
