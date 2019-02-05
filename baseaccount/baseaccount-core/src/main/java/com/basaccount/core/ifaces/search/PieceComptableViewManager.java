
package com.basaccount.core.ifaces.search;

import com.basaccount.model.search.PieceComptableView;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 05 11:56:27 WAT 2019
 * 
 */
public interface PieceComptableViewManager
    extends GenericManager<PieceComptableView, Long>
{

    public final static String SERVICE_NAME = "PieceComptableViewManager";

}
