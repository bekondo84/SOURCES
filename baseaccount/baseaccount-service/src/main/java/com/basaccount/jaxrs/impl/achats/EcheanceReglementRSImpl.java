
package com.basaccount.jaxrs.impl.achats;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.achats.EcheanceReglementManagerRemote;
import com.basaccount.jaxrs.ifaces.achats.EcheanceReglementRS;
import com.basaccount.model.achats.EcheanceReglement;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.RSNumber;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Feb 08 21:54:41 WAT 2019
 * 
 */
@Path("/echeancereglement")
public class EcheanceReglementRSImpl
    extends AbstractGenericService<EcheanceReglement, Long>
    implements EcheanceReglementRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "EcheanceReglementManagerImpl", interf = EcheanceReglementManagerRemote.class)
    protected EcheanceReglementManagerRemote manager;

    public EcheanceReglementRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<EcheanceReglement, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }    
    
    

}
