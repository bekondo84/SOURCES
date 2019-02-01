
package com.keren.smsgateway.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.model.SMSConfiguration;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 01 16:02:27 WAT 2019
 * 
 */
public interface SMSConfigurationManager
    extends GenericManager<SMSConfiguration, Long>
{

    public final static String SERVICE_NAME = "SMSConfigurationManager";

}
