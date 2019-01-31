
package com.keren.smsgateway.core.ifaces;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.model.SMSOUT;


/**
 * Interface etendue par les interfaces locale et remote du manager
 * @since Tue Jan 29 21:41:40 WAT 2019
 * 
 */
public interface SMSOUTManager
    extends GenericManager<SMSOUT, Long>
{

    public final static String SERVICE_NAME = "SMSOUTManager";

}
