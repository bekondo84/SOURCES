
package com.basaccount.dao.ifaces.ventes;

import com.basaccount.model.ventes.ReglementClient;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Jan 16 11:12:50 WAT 2019
 * 
 */
public interface ReglementClientDAO
    extends GenericDAO<ReglementClient, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ReglementClientDAO";

}
