
package com.keren.jaxrs.impl.carrieres;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.core.ifaces.carrieres.NominationManagerRemote;
import com.keren.jaxrs.ifaces.carrieres.NominationRS;
import com.keren.model.carrieres.Cessation;
import com.keren.model.carrieres.Nomination;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;


/**
 * Classe d'implementation du Web Service JAX-RS
 * @since Tue Apr 10 13:14:15 GMT+01:00 2018
 * 
 */
@Path("/nomination")
public class NominationRSImpl
    extends AbstractGenericService<Nomination, Long>
    implements NominationRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerenrh", name = "NominationManagerImpl", interf = NominationManagerRemote.class)
    protected NominationManagerRemote manager;

    public NominationRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Nomination, Long> getManager() {
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
   			meta = MetaDataUtil.getMetaData(new Nomination(), new HashMap<String, MetaData>()
   			, new ArrayList<String>());
   			MetaColumn workbtn = new MetaColumn("button", "work1", "Valider", false, "workflow", null);
   	        workbtn.setValue("{'model':'kerenrh','entity':'nomination','method':'valide'}");
   	        workbtn.setStates(new String[]{"etabli"});
   	        workbtn.setPattern("btn btn-success");
   	        meta.getHeader().add(workbtn);  
//   	        workbtn = new MetaColumn("button", "work1", "Annuler", false, "workflow", null);
//   	        workbtn.setValue("{'model':'kerenrh','entity':'nomination','method':'annule'}");
//   	        workbtn.setStates(new String[]{"etabli"});
//   	        workbtn.setPattern("btn btn-danger");
//   	        meta.getHeader().add(workbtn);   
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
		Nomination entity = manager.find("id", (Long) id);
		if(!entity.getState().equalsIgnoreCase("etabli")){
			throw new KerenExecption("Nomination déjà validée ou rejetée");
		}
		super.processBeforeDelete(entity);
	}

	@Override
	protected void processBeforeSave(Nomination entity) {
		// TODO Auto-generated method stub
		if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
			throw new KerenExecption("La Reference de la nomination est obligatoire");
		}else if(entity.getSalarie()==null){
			throw new KerenExecption("Le Salarié concerné par la nomination est obligatoire");
		}else if(entity.getPosteN()==null){
			throw new KerenExecption("Le Poste concerné par la nomination est obligatoire");
		}else if(entity.getFonctionN()==null){
			throw new KerenExecption("La Fonction concerné par la nomination est obligatoire");
		}
		entity.setState("etabli");
		super.processBeforeSave(entity);
	}

	@Override
	protected void processBeforeUpdate(Nomination entity) {
		// TODO Auto-generated method stub
		if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
			throw new KerenExecption("La Reference de la nomination est obligatoire");
		}else if(entity.getSalarie()==null){
			throw new KerenExecption("Le Salarié concerné par la nomination est obligatoire");
		}else if(entity.getPosteN()==null){
			throw new KerenExecption("Le Poste concerné par la nomination est obligatoire");
		}else if(entity.getFonctionN()==null){
			throw new KerenExecption("La Fonction concerné par la nomination est obligatoire");
		}
		super.processBeforeUpdate(entity);
	}

	@Override
	public Nomination valide(HttpHeaders headers, Nomination entity) {
		// TODO Auto-generated method stub
		if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
			throw new KerenExecption("La Reference de la nomination est obligatoire");
		}else if(entity.getSalarie()==null){
			throw new KerenExecption("Le Salarié concerné par la nomination est obligatoire");
		}else if(entity.getPosteN()==null){
			throw new KerenExecption("Le Poste concerné par la nomination est obligatoire");
		}else if(entity.getFonctionN()==null){
			throw new KerenExecption("La Fonction concerné par la nomination est obligatoire");
		}else if(entity.getDecision()==null){
			throw new KerenExecption("La date de decision est obligatoire");
		}else if(entity.getDate()==null){
			throw new KerenExecption("La date de nomination est obligatoire");
		}
		return null;
	}

	@Override
	public Nomination annule(HttpHeaders headers, Nomination entity) {
		// TODO Auto-generated method stub
		if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
			throw new KerenExecption("La Reference de la nomination est obligatoire");
		}else if(entity.getSalarie()==null){
			throw new KerenExecption("Le Salarié concerné par la nomination est obligatoire");
		}else if(entity.getPosteN()==null){
			throw new KerenExecption("Le Poste concerné par la nomination est obligatoire");
		}else if(entity.getFonctionN()==null){
			throw new KerenExecption("La Fonction concerné par la nomination est obligatoire");
		}else if(entity.getDecision()==null){
			throw new KerenExecption("La date de decision est obligatoire");
		}else if(entity.getDate()==null){
			throw new KerenExecption("La date de nomination est obligatoire");
		}
		return null;
	}

}
