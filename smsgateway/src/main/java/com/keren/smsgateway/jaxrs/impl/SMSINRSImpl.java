
package com.keren.smsgateway.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.core.ifaces.SMSINManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.SMSINRS;
import com.keren.smsgateway.model.SMSIN;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;


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

}
