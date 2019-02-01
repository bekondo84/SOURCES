
package com.keren.smsgateway.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.smsgateway.model.SendSMS;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Fri Feb 01 10:34:32 WAT 2019
 * 
 */
public interface SendSMSDAO
    extends GenericDAO<SendSMS, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SendSMSDAO";

}
