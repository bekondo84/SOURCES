
package com.basaccount.core.ifaces.achats;

import com.basaccount.model.achats.NoteFrais;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.ventes.NoteFraisVente;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Fri Mar 16 09:30:48 GMT+01:00 2018
 * 
 */
public interface NoteFraisManager
    extends GenericManager<NoteFrais, Long>
{

    public final static String SERVICE_NAME = "NoteFraisManager";
    
    /**
     * 
     * @param entity
     * @param periode
     * @return 
     */
     public NoteFrais valider(NoteFrais entity,PeriodeComptable periode);

}
