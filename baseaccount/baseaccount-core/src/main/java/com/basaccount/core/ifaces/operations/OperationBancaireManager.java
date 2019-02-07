
package com.basaccount.core.ifaces.operations;

import com.basaccount.model.operations.OperationBancaire;
import com.basaccount.model.operations.PieceComptable;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;


/**
 * Interface etendue par les interfaces locale et remote du manager

 * @since Wed Jan 16 11:53:27 WAT 2019
 * 
 */
public interface OperationBancaireManager
    extends GenericManager<OperationBancaire, Long>
{

    public final static String SERVICE_NAME = "OperationBancaireManager";

    /**
     * 
     * @param entity
     * @return 
     */
    public OperationBancaire valider(OperationBancaire entity);
}
