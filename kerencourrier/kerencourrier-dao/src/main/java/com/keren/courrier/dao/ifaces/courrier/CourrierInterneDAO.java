
package com.keren.courrier.dao.ifaces.courrier;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.courrier.model.courrier.CourrierInterne;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Jul 18 13:37:32 GMT+01:00 2018
 * 
 */
public interface CourrierInterneDAO
    extends GenericDAO<CourrierInterne, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "CourrierInterneDAO";
    
    public long deleteCourrierRAD(CourrierInterne entity);

}
