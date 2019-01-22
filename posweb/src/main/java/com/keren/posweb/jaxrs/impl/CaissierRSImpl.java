
package com.keren.posweb.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.keren.posweb.core.ifaces.CaissierManagerRemote;
import com.keren.posweb.jaxrs.ifaces.CaissierRS;
import com.keren.posweb.model.Caissier;
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

 * @since Mon Jan 21 13:47:32 WAT 2019
 * 
 */
@Path("/caissier")
public class CaissierRSImpl
    extends AbstractGenericService<Caissier, Long>
    implements CaissierRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "posweb", name = "CaissierManagerImpl", interf = CaissierManagerRemote.class)
    protected CaissierManagerRemote manager;

    public CaissierRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Caissier, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("posweb");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new Caissier(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(CaissierRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CaissierRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Caissier enable(HttpHeaders headers, Caissier entity) {
         //To change body of generated methods, choose Tools | Templates.
        entity.setState("enable");
        manager.update(entity.getId(), entity);
        return entity;
    }

    @Override
    public Caissier desable(HttpHeaders headers, Caissier entity) {
        entity.setState("desable");
        manager.update(entity.getId(), entity);
        return entity;
    }

    @Override
    public Caissier getCasherByMail(HttpHeaders headers, String mail) {
         //To change body of generated methods, choose Tools | Templates.
        return manager.getCassierWithAccount(mail);
    }
    
    

}
