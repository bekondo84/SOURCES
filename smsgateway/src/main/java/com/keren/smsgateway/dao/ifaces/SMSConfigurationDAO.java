
package com.keren.smsgateway.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.smsgateway.model.SMSConfiguration;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 01 16:02:26 WAT 2019
 * 
 */
public interface SMSConfigurationDAO
    extends GenericDAO<SMSConfiguration, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SMSConfigurationDAO";

}
