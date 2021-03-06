
package com.keren.dao.ifaces.comptabilite;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.model.comptabilite.Taxe;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Feb 14 12:53:09 GMT+01:00 2018
 * 
 */
public interface TaxeDAO
    extends GenericDAO<Taxe, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "TaxeDAO";

}
