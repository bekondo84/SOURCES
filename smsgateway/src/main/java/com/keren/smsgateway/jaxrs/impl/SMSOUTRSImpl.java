
package com.keren.smsgateway.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.smsgateway.core.ifaces.SMSOUTManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.SMSOUTRS;
import com.keren.smsgateway.model.SMSOUT;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Tue Jan 29 21:41:40 WAT 2019
 * 
 */
@Path("/smsout")
public class SMSOUTRSImpl
    extends AbstractGenericService<SMSOUT, Long>
    implements SMSOUTRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "smsgateway", name = "SMSOUTManagerImpl", interf = SMSOUTManagerRemote.class)
    protected SMSOUTManagerRemote manager;

    public SMSOUTRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SMSOUT, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("smsgateway");
    }

}
