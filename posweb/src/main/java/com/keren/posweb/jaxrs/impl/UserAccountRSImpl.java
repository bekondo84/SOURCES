
package com.keren.posweb.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.posweb.core.ifaces.UserAccountManagerRemote;
import com.keren.posweb.jaxrs.ifaces.UserAccountRS;
import com.keren.posweb.model.UserAccount;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Mon Jan 21 15:03:49 WAT 2019
 * 
 */
@Path("/useraccount")
public class UserAccountRSImpl
    extends AbstractGenericService<UserAccount, Long>
    implements UserAccountRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "posweb", name = "UserAccountManagerImpl", interf = UserAccountManagerRemote.class)
    protected UserAccountManagerRemote manager;

    public UserAccountRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<UserAccount, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("posweb");
    }

}
