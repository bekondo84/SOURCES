
package com.basaccount.jaxrs.impl.operations;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.EcritureBanqueManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.EcritureBanqueRS;
import com.basaccount.model.operations.EcritureBanque;
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

 * @since Wed Jan 16 11:53:29 WAT 2019
 * 
 */
@Path("/ecriturebanque")
public class EcritureBanqueRSImpl
    extends AbstractGenericService<EcritureBanque, Long>
    implements EcritureBanqueRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "EcritureBanqueManagerImpl", interf = EcritureBanqueManagerRemote.class)
    protected EcritureBanqueManagerRemote manager;

    public EcritureBanqueRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<EcritureBanque, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new EcritureBanque(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(EcritureBanqueRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EcritureBanqueRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
