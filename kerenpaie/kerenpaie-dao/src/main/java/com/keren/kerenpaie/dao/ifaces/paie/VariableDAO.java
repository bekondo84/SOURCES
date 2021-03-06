
package com.keren.kerenpaie.dao.ifaces.paie;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.kerenpaie.model.paie.Variable;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Mar 06 12:50:48 GMT+01:00 2018
 * 
 */
public interface VariableDAO
    extends GenericDAO<Variable, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "VariableDAO";

}
