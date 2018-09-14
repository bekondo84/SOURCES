
package com.teratech.gmao.dao.ifaces.base;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.gmao.model.base.CalendrierIntervenant;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Sat Jul 14 13:40:50 GMT+01:00 2018
 * 
 */
public interface CalendrierIntervenantDAO
    extends GenericDAO<CalendrierIntervenant, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "CalendrierIntervenantDAO";

}
