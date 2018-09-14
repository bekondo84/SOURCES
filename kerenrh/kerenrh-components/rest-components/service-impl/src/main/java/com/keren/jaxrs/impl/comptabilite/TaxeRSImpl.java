
package com.keren.jaxrs.impl.comptabilite;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.core.ifaces.comptabilite.TaxeManagerRemote;
import com.keren.jaxrs.ifaces.comptabilite.TaxeRS;
import com.keren.model.comptabilite.Compte;
import com.keren.model.comptabilite.Taxe;
import com.keren.model.employes.Categorie;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Wed Feb 14 12:53:10 GMT+01:00 2018
 * 
 */
@Path("/taxe")
public class TaxeRSImpl
    extends AbstractGenericService<Taxe, Long>
    implements TaxeRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerenrh", name = "TaxeManagerImpl", interf = TaxeManagerRemote.class)
    protected TaxeManagerRemote manager;

    public TaxeRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Taxe, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kerenrh");
    }
    
    @Override
    public MetaData getMetaData(HttpHeaders headers) {
            // TODO Auto-generated method stub
            try {
                    return MetaDataUtil.getMetaData(new Taxe(),new HashMap<String, MetaData>()
                                    , new ArrayList<String>());
            } catch (Exception e) {
                    // TODO Auto-generated catch block
                    throw new WebApplicationException(e);
            }
    }
    
    @Override
    protected void processBeforeUpdate(Taxe entity) {
        
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le code est obligatoire");
        }
        super.processBeforeUpdate(entity);
    }

    @Override
    protected void processBeforeSave(Taxe entity) {
        
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le code est obligatoire");
        }
        super.processBeforeSave(entity);
    }

}
