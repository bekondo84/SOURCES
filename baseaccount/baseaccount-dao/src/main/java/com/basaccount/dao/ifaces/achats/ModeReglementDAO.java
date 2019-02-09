
package com.basaccount.dao.ifaces.achats;

import com.basaccount.model.achats.ModeReglement;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 08 22:34:45 WAT 2019
 * 
 */
public interface ModeReglementDAO
    extends GenericDAO<ModeReglement, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ModeReglementDAO";

}
