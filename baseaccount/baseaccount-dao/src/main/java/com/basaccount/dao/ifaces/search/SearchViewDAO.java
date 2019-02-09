
package com.basaccount.dao.ifaces.search;

import com.basaccount.model.search.SearchView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Thu Feb 07 16:18:50 WAT 2019
 * 
 */
public interface SearchViewDAO
    extends GenericDAO<SearchView, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SearchViewDAO";

}
