
package com.kerenedu.reglement;

import java.util.List;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Mar 06 16:43:58 CET 2018
 * 
 */
public interface PaiementManager
    extends GenericManager<Paiement, Long>
{

    public final static String SERVICE_NAME = "PaiementManager";
    
    public List<Paiement> getCriteres(Paiement critere);

}
