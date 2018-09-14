
package com.keren.jaxrs.impl.carrieres;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.core.ifaces.carrieres.ReclassementManagerRemote;
import com.keren.jaxrs.ifaces.carrieres.ReclassementRS;
import com.keren.model.carrieres.Avancement;
import com.keren.model.carrieres.Bonification;
import com.keren.model.carrieres.Reclassement;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Tue Apr 10 13:14:15 GMT+01:00 2018
 * 
 */
@Path("/reclassement")
public class ReclassementRSImpl
    extends AbstractGenericService<Reclassement, Long>
    implements ReclassementRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerenrh", name = "ReclassementManagerImpl", interf = ReclassementManagerRemote.class)
    protected ReclassementManagerRemote manager;

    public ReclassementRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Reclassement, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kerenrh");
    }
    
    @Override
	public MetaData getMetaData(HttpHeaders headers) {
		// TODO Auto-generated method stub
		MetaData meta = null;
		try {
			meta = MetaDataUtil.getMetaData(new Reclassement(), new HashMap<String, MetaData>()
			, new ArrayList<String>());
			MetaColumn workbtn = new MetaColumn("button", "work1", "Valider", false, "workflow", null);
	        workbtn.setValue("{'model':'kerenrh','entity':'reclassement','method':'valide'}");
	        workbtn.setStates(new String[]{"etabli"});
	        workbtn.setPattern("btn btn-success");
	        meta.getHeader().add(workbtn);  
//	        workbtn = new MetaColumn("button", "work1", "Annuler", false, "workflow", null);
//	        workbtn.setValue("{'model':'kerenrh','entity':'reclassement','method':'annule'}");
//	        workbtn.setStates(new String[]{"etabli"});
//	        workbtn.setPattern("btn btn-danger");
//	        meta.getHeader().add(workbtn);   
	        MetaColumn stautsbar = new MetaColumn("workflow", "state", "State", false, "statusbar", null);
	        meta.getHeader().add(stautsbar);	
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meta;
	}    
        

	@Override
	protected void processBeforeDelete(Object id) {
		// TODO Auto-generated method stub
		Reclassement entity = manager.find("id", (Long) id);
		if(entity.getState().trim().equalsIgnoreCase("valide")){
			throw new KerenExecption("Reclassement déjà validé <br/>Suppresion impossible");
		}
		super.processBeforeDelete(entity);
	}

	@Override
	protected void processBeforeSave(Reclassement entity) {
		// TODO Auto-generated method stub
		if(entity.getSalarie()==null){
			throw new KerenExecption("L'employé est obligatoire");
		}else if(entity.getCategorieA()==null){
			throw new KerenExecption("L'ancien echelon est obligatoire");
		}else if(entity.getCategorieN()==null){
			throw new KerenExecption("Le nouvel echelon est obligatoire");
		}else if(entity.getDenreg()==null){
			throw new KerenExecption("La date d'enregisttrement est obligatoire");
		}
		entity.setState("etabli");
		super.processBeforeSave(entity);
	}

	@Override
	protected void processBeforeUpdate(Reclassement entity) {
		// TODO Auto-generated method stub
		if(entity.getSalarie()==null){
			throw new KerenExecption("L'employé est obligatoire");
		}else if(entity.getCategorieA()==null){
			throw new KerenExecption("L'ancien echelon est obligatoire");
		}else if(entity.getCategorieN()==null){
			throw new KerenExecption("Le nouvel echelon est obligatoire");
		}else if(entity.getDenreg()==null){
			throw new KerenExecption("La date d'enregisttrement est obligatoire");
		}
		super.processBeforeUpdate(entity);
	}

	@Override
	public Reclassement valide(HttpHeaders headers, Reclassement entity) {
		// TODO Auto-generated method stub
		if(entity.getSalarie()==null){
			throw new KerenExecption("L'employé est obligatoire");
		}else if(entity.getCategorieA()==null){
			throw new KerenExecption("L'ancien echelon est obligatoire");
		}else if(entity.getCategorieN()==null){
			throw new KerenExecption("Le nouvel echelon est obligatoire");
		}else if(entity.getDenreg()==null){
			throw new KerenExecption("La date d'enregisttrement est obligatoire");
		}else if(entity.getDeffet()==null){
			throw new KerenExecption("La date de prise d'effet est obligatoire");
		}
		return manager.valide(entity);
	}

	@Override
	public Reclassement annule(HttpHeaders headers, Reclassement entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
