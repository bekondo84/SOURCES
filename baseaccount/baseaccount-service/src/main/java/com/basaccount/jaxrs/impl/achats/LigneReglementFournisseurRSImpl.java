
package com.basaccount.jaxrs.impl.achats;

import com.basaccount.core.ifaces.achats.EcheanceReglementManagerRemote;
import javax.ws.rs.Path;
import com.basaccount.core.ifaces.achats.LigneReglementFournisseurManagerRemote;
import com.basaccount.jaxrs.ifaces.achats.LigneReglementFournisseurRS;
import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.achats.LigneReglementFournisseur;
import com.basaccount.model.ventes.LigneReglementClient;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.FilterPredicat;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.RSNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Sat Feb 09 21:04:22 WAT 2019
 * 
 */
@Path("/lignereglementfournisseur")
public class LigneReglementFournisseurRSImpl
    extends AbstractGenericService<LigneReglementFournisseur, Long>
    implements LigneReglementFournisseurRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "LigneReglementFournisseurManagerImpl", interf = LigneReglementFournisseurManagerRemote.class)
    protected LigneReglementFournisseurManagerRemote manager;
    
    @Manager(application = "baseaccount", name = "EcheanceReglementManagerImpl", interf = EcheanceReglementManagerRemote.class)
    protected EcheanceReglementManagerRemote echeancemanager;
    
    public LigneReglementFournisseurRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<LigneReglementFournisseur, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new LigneReglementFournisseur(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(LigneReglementFournisseurRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LigneReglementFournisseurRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public RSNumber count(HttpHeaders headers) {
        Gson gson =new Gson();
        List contraints = new ArrayList();
        if(headers.getRequestHeader("predicats")!=null&&!headers.getRequestHeader("predicats").isEmpty()){
            contraints = gson.fromJson(headers.getRequestHeader("predicats").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        } //end if(headers.getRequestHeader("predicats")!=null){     
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat filter = (FilterPredicat) obj ;
                if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
                        &&filter.getValue()!=null&&!filter.getValue().isEmpty()){
                        if(filter.getFieldName().equalsIgnoreCase("fournisseur")){
                            container.addEq("tier.code", filter.getValue());
                        }else if(filter.getFieldName().equalsIgnoreCase("modereglement")){
                            container.addEq("mode.code", filter.getValue());
                        }                        
                }//end if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
            }//end  for(Object obj : contraints)
        }//end if(contraints!=null&&!contraints.isEmpty())
        container.addEq("etat", Boolean.TRUE);
        return new RSNumber(echeancemanager.count(container.getPredicats())); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LigneReglementFournisseur> filter(HttpHeaders headers, int firstResult, int maxResult) {
        Gson gson =new Gson();
        List contraints = new ArrayList();
        if(headers.getRequestHeader("predicats")!=null&&!headers.getRequestHeader("predicats").isEmpty()){
            contraints = gson.fromJson(headers.getRequestHeader("predicats").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        } //end if(headers.getRequestHeader("predicats")!=null){     
        System.out.println(LigneReglementFournisseurRSImpl.class.toString()+".filter(HttpHeaders headers, int firstResult, int maxResult) ====== "+contraints);
        RestrictionsContainer container = RestrictionsContainer.newInstance();
         if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat filter = (FilterPredicat) obj ;
                if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
                        &&filter.getValue()!=null&&!filter.getValue().isEmpty()){
                        if(filter.getFieldName().equalsIgnoreCase("fournisseur")){
                            container.addEq("tier.code", filter.getValue());
                        }else if(filter.getFieldName().equalsIgnoreCase("modereglement")){
                            container.addEq("mode.code", filter.getValue());
                        }                        
                }//end if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
            }//end  for(Object obj : contraints)
        }//end if(contraints!=null&&!contraints.isEmpty())
        container.addEq("etat", Boolean.FALSE);
        List<EcheanceReglement> echeances = echeancemanager.filter(container.getPredicats(), null, null, firstResult, maxResult);        
        List<LigneReglementFournisseur> reglements = new ArrayList<LigneReglementFournisseur>();
        for(EcheanceReglement echeance:echeances){
            reglements.add(new LigneReglementFournisseur(echeance));
        }//end for(EcheanceReglement echeance:echeances){
        return reglements; //To change body of generated methods, choose Tools | Templates.
    }
    
}
