
package com.kerenedu.reglement;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.kerenedu.configuration.CacheMemory;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Sun Jul 08 15:59:49 WAT 2018
 * 
 */
@Path("/depense")
public class DepenseRSImpl
    extends AbstractGenericService<Depense, Long>
    implements DepenseRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kereneducation", name = "DepenseManagerImpl", interf = DepenseManagerRemote.class)
    protected DepenseManagerRemote manager;

    public DepenseRSImpl() {
        super();
    }
    
    @Override
   	public MetaData getMetaData(HttpHeaders headers) {
   		// TODO Auto-generated method stub
   		try {
   			return MetaDataUtil.getMetaData(new Depense(), new HashMap<String, MetaData>(),new ArrayList<String>());
   		} catch (InstantiationException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} catch (IllegalAccessException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return null;
   	}
    

    @Override
	protected void processBeforeSave(Depense entity) {
		entity.setAnneScolaire(CacheMemory.getCurrentannee());
		super.processBeforeSave(entity);
	}

	/**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Depense, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kereneducation");
    }

}
