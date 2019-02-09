
package com.basaccount.dao.ifaces.ventes;

import com.basaccount.model.ventes.LigneReglementClient;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 08 21:59:56 WAT 2019
 * 
 */
public interface LigneReglementClientDAO
    extends GenericDAO<LigneReglementClient, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "LigneReglementClientDAO";

}
