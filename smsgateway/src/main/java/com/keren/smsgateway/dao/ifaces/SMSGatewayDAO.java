
package com.keren.smsgateway.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.smsgateway.model.SMSGateway;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 01 16:02:26 WAT 2019
 * 
 */
public interface SMSGatewayDAO
    extends GenericDAO<SMSGateway, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SMSGatewayDAO";

}
