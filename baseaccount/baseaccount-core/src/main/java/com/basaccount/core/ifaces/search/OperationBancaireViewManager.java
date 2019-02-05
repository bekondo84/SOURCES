
package com.basaccount.core.ifaces.search;

import com.basaccount.model.search.OperationBancaireView;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Feb 05 11:56:27 WAT 2019
 * 
 */
public interface OperationBancaireViewManager
    extends GenericManager<OperationBancaireView, Long>
{

    public final static String SERVICE_NAME = "OperationBancaireViewManager";

}
