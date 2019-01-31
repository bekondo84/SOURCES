
package com.keren.smsgateway.dao.ifaces;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.keren.smsgateway.model.SMSCALL;


/**
 * Interface etendue par les interfaces locale et remote de la DAO
 * @since Tue Jan 29 21:41:39 WAT 2019
 * 
 */
public interface SMSCALLDAO
    extends GenericDAO<SMSCALL, Long>
{

    /**
     * Nom du service
     * 
     */
    public final static String SERVICE_NAME = "SMSCALLDAO";

}
