
package com.keren.jaxrs.impl.structures;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.keren.core.ifaces.structures.EtablissementManagerRemote;
import com.keren.jaxrs.ifaces.structures.EtablissementRS;
import com.keren.model.structures.Etablissement;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Tue Apr 10 17:59:57 GMT+01:00 2018
 * 
 */
@Path("/etablissement")
public class EtablissementRSImpl
    extends AbstractGenericService<Etablissement, Long>
    implements EtablissementRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerenrh", name = "EtablissementManagerImpl", interf = EtablissementManagerRemote.class)
    protected EtablissementManagerRemote manager;

    public EtablissementRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Etablissement, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kerenrh");
    }

}
