
package com.basaccount.dao.ifaces.search;

import com.basaccount.model.search.JournalSaisieView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Thu Jan 17 09:54:17 WAT 2019
 * 
 */
public interface JournalSaisieViewDAO
    extends GenericDAO<JournalSaisieView, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "JournalSaisieViewDAO";

}
