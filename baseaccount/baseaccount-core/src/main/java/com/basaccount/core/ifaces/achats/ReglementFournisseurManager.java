
package com.basaccount.core.ifaces.achats;

import com.basaccount.model.achats.ReglementFournisseur;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Wed Jan 16 10:27:09 WAT 2019
 * 
 */
public interface ReglementFournisseurManager
    extends GenericManager<ReglementFournisseur, Long>
{

    public final static String SERVICE_NAME = "ReglementFournisseurManager";

}
