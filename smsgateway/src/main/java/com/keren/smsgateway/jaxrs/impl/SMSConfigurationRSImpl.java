
package com.keren.smsgateway.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.keren.smsgateway.core.ifaces.SMSConfigurationManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.SMSConfigurationRS;
import com.keren.smsgateway.model.SMSConfiguration;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Feb 01 16:02:28 WAT 2019
 * 
 */
@Path("/smsconfiguration")
public class SMSConfigurationRSImpl
    extends AbstractGenericService<SMSConfiguration, Long>
    implements SMSConfigurationRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "smsgateway", name = "SMSConfigurationManagerImpl", interf = SMSConfigurationManagerRemote.class)
    protected SMSConfigurationManagerRemote manager;

    public SMSConfigurationRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SMSConfiguration, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("smsgateway");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new SMSConfiguration(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(SMSConfigurationRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SMSConfigurationRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    
}
