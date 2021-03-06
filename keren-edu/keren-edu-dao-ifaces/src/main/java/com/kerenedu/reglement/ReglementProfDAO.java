
package com.kerenedu.reglement;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Mar 07 17:04:44 CET 2018
 * 
 */
public interface ReglementProfDAO
    extends GenericDAO<ReglementProf, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ReglementProfDAO";

}
