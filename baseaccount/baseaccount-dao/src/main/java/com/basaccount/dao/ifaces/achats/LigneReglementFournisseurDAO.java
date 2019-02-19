
package com.basaccount.dao.ifaces.achats;

import com.basaccount.model.achats.LigneReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Sat Feb 09 21:04:21 WAT 2019
 * 
 */
public interface LigneReglementFournisseurDAO
    extends GenericDAO<LigneReglementFournisseur, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "LigneReglementFournisseurDAO";

}
