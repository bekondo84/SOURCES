
package com.keren.smsgateway.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.smsgateway.model.ProgramSMS;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 01 10:56:14 WAT 2019
 * 
 */
public interface ProgramSMSDAO
    extends GenericDAO<ProgramSMS, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "ProgramSMSDAO";

}
