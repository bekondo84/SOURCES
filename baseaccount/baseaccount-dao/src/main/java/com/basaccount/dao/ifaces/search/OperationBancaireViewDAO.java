
package com.basaccount.dao.ifaces.search;

import com.basaccount.model.search.OperationBancaireView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Feb 05 11:56:27 WAT 2019
 * 
 */
public interface OperationBancaireViewDAO
    extends GenericDAO<OperationBancaireView, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "OperationBancaireViewDAO";

}
