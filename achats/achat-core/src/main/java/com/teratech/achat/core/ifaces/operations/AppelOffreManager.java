
package com.teratech.achat.core.ifaces.operations;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.teratech.achat.model.operations.AppelOffre;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Tue Feb 27 14:29:39 GMT+01:00 2018
 * 
 */
public interface AppelOffreManager
    extends GenericManager<AppelOffre, Long>
{

    public final static String SERVICE_NAME = "AppelOffreManager";
    
    public AppelOffre confirmer(AppelOffre entity);
    
    public AppelOffre selectionner(AppelOffre entity);
    
     public AppelOffre annuler(AppelOffre entity);
     
     public AppelOffre termine(AppelOffre entity);

}
