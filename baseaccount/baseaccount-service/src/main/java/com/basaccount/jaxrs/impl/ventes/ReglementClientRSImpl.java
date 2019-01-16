
package com.basaccount.jaxrs.impl.ventes;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.ventes.ReglementClientManagerRemote;
import com.basaccount.jaxrs.ifaces.ventes.ReglementClientRS;
import com.basaccount.model.ventes.ReglementClient;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
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

 * @since Wed Jan 16 11:12:51 WAT 2019
 * 
 */
@Path("/reglementclient")
public class ReglementClientRSImpl
    extends AbstractGenericService<ReglementClient, Long>
    implements ReglementClientRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ReglementClientManagerImpl", interf = ReglementClientManagerRemote.class)
    protected ReglementClientManagerRemote manager;

    public ReglementClientRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ReglementClient, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new ReglementClient(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ReglementClientRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReglementClientRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
