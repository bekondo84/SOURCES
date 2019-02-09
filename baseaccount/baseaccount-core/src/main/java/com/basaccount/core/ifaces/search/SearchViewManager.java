
package com.basaccount.core.ifaces.search;

import com.basaccount.model.search.SearchView;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Thu Feb 07 16:18:50 WAT 2019
 * 
 */
public interface SearchViewManager
    extends GenericManager<SearchView, Long>
{

    public final static String SERVICE_NAME = "SearchViewManager";

}
