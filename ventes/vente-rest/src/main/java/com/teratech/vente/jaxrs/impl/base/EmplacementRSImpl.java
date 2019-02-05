
package com.teratech.vente.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.vente.core.ifaces.base.EmplacementManagerRemote;
import com.teratech.vente.jaxrs.ifaces.base.EmplacementRS;
import com.teratech.vente.model.base.Emplacement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Jan 04 08:13:39 WAT 2019
 * 
 */
@Path("/emplacement")
public class EmplacementRSImpl
    extends AbstractGenericService<Emplacement, Long>
    implements EmplacementRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechvente", name = "EmplacementManagerImpl", interf = EmplacementManagerRemote.class)
    protected EmplacementManagerRemote manager;

    public EmplacementRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Emplacement, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechvente");
    }
    
     @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            return MetaDataUtil.getMetaData(new Emplacement(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
        } catch (InstantiationException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

     @Override
    public Emplacement delete(HttpHeaders headers, Long id) {
        try{
            return super.delete(headers, id);
        } catch(Exception ex){
           throw  new KerenExecption("emplacement.delete.error");
        }//To change body of generated methods, choose Tools | Templates.
    }
}
