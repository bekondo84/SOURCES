
package com.keren.smsgateway.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.model.SMSCALL;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Jan 29 21:41:40 WAT 2019
 * 
 */
public interface SMSCALLManager
    extends GenericManager<SMSCALL, Long>
{

    public final static String SERVICE_NAME = "SMSCALLManager";

}
