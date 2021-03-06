
package com.basaccount.jaxrs.impl.achats;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.achats.ModeReglementManagerRemote;
import com.basaccount.jaxrs.ifaces.achats.ModeReglementRS;
import com.basaccount.model.achats.ModeReglement;
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

 * @since Fri Feb 08 22:34:46 WAT 2019
 * 
 */
@Path("/modereglement")
public class ModeReglementRSImpl
    extends AbstractGenericService<ModeReglement, Long>
    implements ModeReglementRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "ModeReglementManagerImpl", interf = ModeReglementManagerRemote.class)
    protected ModeReglementManagerRemote manager;

    public ModeReglementRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ModeReglement, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta =null;
        try {
            meta = MetaDataUtil.getMetaData(new ModeReglement(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ModeReglementRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ModeReglementRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    
}
