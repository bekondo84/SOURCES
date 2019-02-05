
package com.teratech.achat.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.achat.core.ifaces.base.UniteAchatManagerRemote;
import com.teratech.achat.jaxrs.ifaces.base.UniteAchatRS;
import com.teratech.achat.model.base.Civilite;
import com.teratech.achat.model.base.UniteAchat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Tue Feb 27 14:29:40 GMT+01:00 2018
 * 
 */
@Path("/uniteachat")
public class UniteAchatRSImpl
    extends AbstractGenericService<UniteAchat, Long>
    implements UniteAchatRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "teratechachat", name = "UniteAchatManagerImpl", interf = UniteAchatManagerRemote.class)
    protected UniteAchatManagerRemote manager;

    public UniteAchatRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<UniteAchat, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("teratechachat");
    }
    
     @Override
    public MetaData getMetaData(HttpHeaders headers) {
        try {
            return MetaDataUtil.getMetaData(new UniteAchat(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
        } catch (InstantiationException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CiviliteRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public UniteAchat delete(HttpHeaders headers, Long id) {
      try{
          return super.delete(headers, id);
      }catch(Exception ex){
        throw new KerenExecption("unite.achat.delete.error");
      } //To change body of generated methods, choose Tools | Templates.
    }

}
