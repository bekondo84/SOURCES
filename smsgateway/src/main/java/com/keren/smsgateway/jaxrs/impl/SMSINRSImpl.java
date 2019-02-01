
package com.keren.smsgateway.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.keren.smsgateway.core.ifaces.SMSINManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.SMSINRS;
import com.keren.smsgateway.model.SMSIN;
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

 * @since Tue Jan 29 21:41:40 WAT 2019
 * 
 */
@Path("/smsin")
public class SMSINRSImpl
    extends AbstractGenericService<SMSIN, Long>
    implements SMSINRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "smsgateway", name = "SMSINManagerImpl", interf = SMSINManagerRemote.class)
    protected SMSINManagerRemote manager;

    public SMSINRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SMSIN, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("smsgateway");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new SMSIN(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(SMSINRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SMSINRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta;
    }

    
}
