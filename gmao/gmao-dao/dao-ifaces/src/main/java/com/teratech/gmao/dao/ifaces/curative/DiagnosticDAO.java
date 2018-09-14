
package com.teratech.gmao.dao.ifaces.curative;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.teratech.gmao.model.curative.Diagnostic;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Mon Jul 16 16:50:23 GMT+01:00 2018
 * 
 */
public interface DiagnosticDAO
    extends GenericDAO<Diagnostic, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "DiagnosticDAO";

}
