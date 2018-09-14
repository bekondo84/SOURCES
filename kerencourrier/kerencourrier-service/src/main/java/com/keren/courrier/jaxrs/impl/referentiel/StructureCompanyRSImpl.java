
package com.keren.courrier.jaxrs.impl.referentiel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.courrier.core.ifaces.referentiel.StructureCompanyManagerRemote;
import com.keren.courrier.jaxrs.ifaces.referentiel.StructureCompanyRS;
import com.keren.courrier.jaxrs.impl.courrier.CourrierRSImpl;
import com.keren.courrier.model.referentiel.StructureCompany;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Wed Jul 18 10:58:44 GMT+01:00 2018
 * 
 */
@Path("/structurecompany")
public class StructureCompanyRSImpl
    extends AbstractGenericService<StructureCompany, Long>
    implements StructureCompanyRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerencourrier", name = "StructureCompanyManagerImpl", interf = StructureCompanyManagerRemote.class)
    protected StructureCompanyManagerRemote manager;

    public StructureCompanyRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<StructureCompany, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kerencourrier");
    }

    
    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new StructureCompany(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(CourrierRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CourrierRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
	public StructureCompany delete(@Context HttpHeaders headers, Long id) {

		// TODO Auto-generated method stub
    	StructureCompany entity = manager.find("id", id);
		try {
				// on supprimme l'objet
				super.delete(headers, id);
	

		} catch (Exception ex) {
			throw new KerenExecption(
					"Suppresion impossible<br/>car cet objet est deja en cours d'utilisation par d'autres objets");
		}

		return entity;
	}
    
}
