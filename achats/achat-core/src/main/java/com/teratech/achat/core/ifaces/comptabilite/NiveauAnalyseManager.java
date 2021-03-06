
package com.teratech.achat.core.ifaces.comptabilite;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.achat.model.comptabilite.NiveauAnalyse;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 27 14:29:39 GMT+01:00 2018
 * 
 */
public interface NiveauAnalyseManager
    extends GenericManager<NiveauAnalyse, Long>
{

    public final static String SERVICE_NAME = "NiveauAnalyseManager";

}
