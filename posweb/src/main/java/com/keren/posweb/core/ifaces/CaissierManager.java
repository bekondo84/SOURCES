
package com.keren.posweb.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.posweb.model.Caissier;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Mon Jan 21 13:47:30 WAT 2019
 * 
 */
public interface CaissierManager
    extends GenericManager<Caissier, Long>
{

    public final static String SERVICE_NAME = "CaissierManager";
    
    public Caissier getCassierWithAccount(Long userid);
    
    public Caissier getCassierWithAccount(String email);

}
