
package com.basaccount.core.ifaces.achats;

import com.basaccount.model.achats.LigneReglementFournisseur;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Sat Feb 09 21:04:21 WAT 2019
 * 
 */
public interface LigneReglementFournisseurManager
    extends GenericManager<LigneReglementFournisseur, Long>
{

    public final static String SERVICE_NAME = "LigneReglementFournisseurManager";

}
