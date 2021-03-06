
package com.basaccount.dao.ifaces.operations;

import com.basaccount.model.operations.OperationBancaire;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Wed Jan 16 11:53:27 WAT 2019
 * 
 */
public interface OperationBancaireDAO
    extends GenericDAO<OperationBancaire, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "OperationBancaireDAO";

}
