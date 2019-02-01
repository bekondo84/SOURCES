
package com.keren.smsgateway.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.model.SMSModel;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Fri Feb 01 10:34:33 WAT 2019
 * 
 */
public interface SMSModelManager
    extends GenericManager<SMSModel, Long>
{

    public final static String SERVICE_NAME = "SMSModelManager";

}
