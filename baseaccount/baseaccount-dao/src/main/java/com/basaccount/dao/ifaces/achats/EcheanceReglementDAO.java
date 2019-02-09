
package com.basaccount.dao.ifaces.achats;

import com.basaccount.model.achats.EcheanceReglement;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 08 21:54:39 WAT 2019
 * 
 */
public interface EcheanceReglementDAO
    extends GenericDAO<EcheanceReglement, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "EcheanceReglementDAO";

}
