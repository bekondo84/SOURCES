
package com.basaccount.dao.ifaces.achats;

import com.basaccount.model.achats.ReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Jan 16 10:27:09 WAT 2019
 * 
 */
public interface ReglementFournisseurDAO
    extends GenericDAO<ReglementFournisseur, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ReglementFournisseurDAO";

}
