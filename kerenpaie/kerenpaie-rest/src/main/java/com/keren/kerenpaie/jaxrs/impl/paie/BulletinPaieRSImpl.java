package com.keren.kerenpaie.jaxrs.impl.paie;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.FileHelper;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.kerenpaie.core.ifaces.paie.BulletinPaieManagerRemote;
import com.keren.kerenpaie.core.ifaces.paie.CacheMemory;
import com.keren.kerenpaie.core.ifaces.paie.MoteurPaieManagerRemote;
import com.keren.kerenpaie.core.ifaces.rapports.ViewBulletinPaieManagerRemote;
import com.keren.kerenpaie.jaxrs.ifaces.paie.BulletinPaieRS;
import com.keren.kerenpaie.jaxrs.impl.rapports.BPaieRSImpl;
import com.keren.kerenpaie.jaxrs.impl.rapports.ViewBulletinPaieRSImpl;
import com.keren.kerenpaie.model.paie.BulletinPaie;
import com.keren.kerenpaie.tools.KerenPaieManagerException;
import com.keren.kerenpaie.tools.report.ReportHelper;
import com.keren.kerenpaie.tools.report.ReportsName;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;

import net.sf.jasperreports.engine.JRException;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Thu Mar 08 12:34:28 GMT+01:00 2018
 * 
 */
@Path("/bulletinpaie")
public class BulletinPaieRSImpl
    extends AbstractGenericService<BulletinPaie, Long>
    implements BulletinPaieRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "kerenpaie", name = "BulletinPaieManagerImpl", interf = BulletinPaieManagerRemote.class)
    protected BulletinPaieManagerRemote manager;
    
    
    @Manager(application = "kerenpaie", name = "MoteurPaieManagerImpl", interf = MoteurPaieManagerRemote.class)
    protected MoteurPaieManagerRemote moteurmanager;
    
    @Manager(application = "kerenpaie", name = "ViewBulletinPaieManagerImpl", interf = ViewBulletinPaieManagerRemote.class)
    protected ViewBulletinPaieManagerRemote viewmanager;
    
    

    public BulletinPaieRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<BulletinPaie, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("kerenpaie");
    }
    
    @Override
	public MetaData getMetaData(HttpHeaders headers) {
		// TODO Auto-generated method stub
		try {
			 MetaData meta = MetaDataUtil.getMetaData(new BulletinPaie(), new HashMap<String, MetaData>(),new ArrayList<String>());
			    MetaColumn workbtn = new MetaColumn("button", "work1", "Lancer le Calcul", false, "object", null);
	            workbtn.setValue("{'model':'kerenpaie','entity':'bulletinpaie','method':'calculer','template':{'this':'object'}}");
	            workbtn.setStates(new String[]{"etabli"});
	            workbtn.setPattern("btn btn-success");
	            meta.getHeader().add(workbtn);
	            workbtn = new MetaColumn("button", "work2", "Imprimer le Bulletin de Paie", false, "report", null);
	            workbtn.setValue("{'model':'kerenpaie','entity':'bulletinpaie','method':'printbulletin'}");
	            workbtn.setStates(new String[]{"etabli"});
//	            workbtn.setPattern("btn btn-primary");
	            meta.getHeader().add(workbtn);
//	            workbtn = new MetaColumn("button", "work3", "Annuler", false, "workflow", null);
//	            workbtn.setValue("{'model':'kerenpaie','entity':'acompte','method':'annule'}");
//	            workbtn.setStates(new String[]{"etabli"});
//	            workbtn.setPattern("btn btn-danger");
//	            meta.getHeader().add(workbtn);	           
	            MetaColumn stautsbar = new MetaColumn("workflow", "state", "State", false, "statusbar", null);
	            meta.getHeader().add(stautsbar);
			    return meta;
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
	protected void processBeforeDelete(Object entity) {
		// TODO Auto-generated method stub
		super.processBeforeDelete(entity);
	}


	@Override
	protected void processBeforeUpdate(BulletinPaie entity) {
		// TODO Auto-generated method stub
		if(entity.getState().trim().equalsIgnoreCase("valide")){
			throw new KerenExecption("Ce bulletin a déjà fait l'objet d'une validation <br/> et par conséquent aucune modification n'est possible");
		}//end if(entity.getState().trim().equalsIgnoreCase("valide")){
		super.processBeforeUpdate(entity);
	}

	@Override
	public BulletinPaie calculer(HttpHeaders headers, BulletinPaie entity) {
		// TODO Auto-generated method stub
		if(entity.getState().trim().equalsIgnoreCase("valide")){
			throw new KerenExecption("Ce bulletin a déjà fait l'objet d'une validation <br/> et par conséquent tout recalcul est  interdite");
		}//end if(entity.getState().trim().equalsIgnoreCase("valide")){
		try{
		  return moteurmanager.eval(entity);
		}catch(KerenPaieManagerException ex){
			throw new KerenExecption(ex.getMessage());
		}
	}
	

	@Override
	public Response printbulletin(HttpHeaders headers, BulletinPaie bulletin) {
		bulletin.setPeriode(CacheMemory.getPeriode());
//		System.out.println("BulletinPaieRSImpl.printbulletin() periode"+bulletin.getPeriode());
		// TODO Auto-generated method stub
		if (bulletin.getPeriode()==null) {
			throw new KerenExecption("Ce bulletin est nulle <br/> ");
		} // end if(entity.getState().trim().equalsIgnoreCase("valide")){
		try {
			return this.buildPdfReport(bulletin);
		} catch (KerenPaieManagerException ex) {
			throw new KerenExecption(ex.getMessage());
		}
	}
	
	
	 /**
     * Methode permettant de retourner les parametres pour le reporting
     *
     * @return java.util.Map
     */
    public Map getReportParameters() {
        return ReportHelperTrt.getReportParameters();
    }


    public Response buildPdfReport(BulletinPaie bulletin) {
        try {
        	 bulletin.setPeriode(CacheMemory.getPeriode());
        	 List<BulletinPaie> records =new ArrayList<BulletinPaie>();
//        	 System.out.println("BulletinPaieRSImpl.buildPdfReport() bulletin select is "+bulletin.getEmploye().getNom());
        	 records.add(bulletin);
              String URL = ReportHelper.templateURL+ReportsName.BULLETIN_PAIE.getName();
              Map parameters = this.getReportParameters();
              return buildReportFomTemplate(FileHelper.getTemporalDirectory().toString(), URL, parameters,
            		  ReportHelperTrt.getBulletintoprint(records));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewBulletinPaieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
            Response.serverError().build();
        }catch (JRException ex) {
            Logger.getLogger(ViewBulletinPaieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        return Response.noContent().build();
    }
    
	public Response buildPdfReportLot(BulletinPaie entity) {
		entity.setPeriode(CacheMemory.getPeriode());

		if (entity.getPeriode() == null) {
			throw new KerenExecption("Bien vouloir renseigner les paramètres d'impression <br/> ");
		}
		System.out.println("LivrePaieRSImpl.buildLivrePaie() debut execution report" + entity.getPeriode().getCode());
		try {
			List<BulletinPaie> records = manager.getCriteres(entity);
			String URL = ReportHelper.templateURL + ReportsName.BULLETIN_PAIE.getName();
			Map parameters = this.getReportParameters();
			return buildReportFomTemplate(FileHelper.getTemporalDirectory().toString(), URL, parameters, ReportHelperTrt.getBulletintoprint(records));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BPaieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
			Response.serverError().build();
		} catch (JRException ex) {
			Logger.getLogger(BPaieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return Response.noContent().build();
	}
	@Override	
	public Response livrepaie(BulletinPaie entity) {
		System.out.println("BulletinPaieRSImpl.livrepaie() build livre paie");
		entity.setPeriode(CacheMemory.getPeriode());

		if (entity.getPeriode() == null) {
			throw new KerenExecption("Bien vouloir renseigner les paramètres d'impression <br/> ");
		}
		System.out.println("LivrePaieRSImpl.buildLivrePaie() debut execution report" + entity.getPeriode().getCode());
		try {
			List<BulletinPaie> records = manager.getCriteres(entity);
			String URL = ReportHelper.templateURL + ReportsName.LIVRE_PAIE.getName();
			Map parameters = this.getReportParameters();
			return buildReportFomTemplate(FileHelper.getTemporalDirectory().toString(), URL, parameters, ReportHelperTrt.getLivretoprint(records));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(BPaieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
			Response.serverError().build();
		} catch (JRException ex) {
			Logger.getLogger(BPaieRSImpl.class.getName()).log(Level.SEVERE, null, ex);
		}

		return Response.noContent().build();
	}



}
