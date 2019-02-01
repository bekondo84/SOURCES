
package com.keren.smsgateway.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.keren.smsgateway.core.ifaces.SMSModelManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.SMSModelRS;
import com.keren.smsgateway.model.SMSModel;
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

 * @since Fri Feb 01 10:34:35 WAT 2019
 * 
 */
@Path("/smsmodel")
public class SMSModelRSImpl
    extends AbstractGenericService<SMSModel, Long>
    implements SMSModelRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "smsgateway", name = "SMSModelManagerImpl", interf = SMSModelManagerRemote.class)
    protected SMSModelManagerRemote manager;

    public SMSModelRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SMSModel, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("smsgateway");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new SMSModel(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(SMSModelRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SMSModelRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
