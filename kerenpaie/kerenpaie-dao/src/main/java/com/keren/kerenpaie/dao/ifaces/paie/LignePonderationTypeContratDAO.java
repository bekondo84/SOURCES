
package com.keren.kerenpaie.dao.ifaces.paie;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.kerenpaie.model.paie.LignePonderationTypeContrat;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Mar 23 14:48:53 GMT+01:00 2018
 * 
 */
public interface LignePonderationTypeContratDAO
    extends GenericDAO<LignePonderationTypeContrat, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "LignePonderationTypeContratDAO";

}
