
package com.basaccount.core.ifaces.search;

import com.basaccount.model.search.JournalSaisieView;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Thu Jan 17 09:54:18 WAT 2019
 * 
 */
public interface JournalSaisieViewManager
    extends GenericManager<JournalSaisieView, Long>
{

    public final static String SERVICE_NAME = "JournalSaisieViewManager";

}
