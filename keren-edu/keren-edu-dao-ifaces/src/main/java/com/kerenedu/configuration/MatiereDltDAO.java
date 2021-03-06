
package com.kerenedu.configuration;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Jul 03 11:27:53 WAT 2018
 * 
 */
public interface MatiereDltDAO
    extends GenericDAO<MatiereDlt, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "MatiereDltDAO";

}
