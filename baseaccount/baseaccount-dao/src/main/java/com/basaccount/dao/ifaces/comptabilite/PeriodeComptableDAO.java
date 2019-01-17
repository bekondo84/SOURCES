
package com.basaccount.dao.ifaces.comptabilite;

import com.basaccount.model.comptabilite.PeriodeComptable;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Jan 16 14:16:26 WAT 2019
 * 
 */
public interface PeriodeComptableDAO
    extends GenericDAO<PeriodeComptable, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "PeriodeComptableDAO";

}
