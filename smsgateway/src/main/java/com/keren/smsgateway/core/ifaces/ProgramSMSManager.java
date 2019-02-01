
package com.keren.smsgateway.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.model.ProgramSMS;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 01 10:56:16 WAT 2019
 * 
 */
public interface ProgramSMSManager
    extends GenericManager<ProgramSMS, Long>
{

    public final static String SERVICE_NAME = "ProgramSMSManager";

}
