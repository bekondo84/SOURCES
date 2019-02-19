
package com.basaccount.jaxrs.impl.operations;

import com.basaccount.core.ifaces.achats.FactureManagerRemote;
import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerRemote;
import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.PieceComptableManagerRemote;
import com.basaccount.core.ifaces.ventes.FactureVenteManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.PieceComptableRS;
import com.basaccount.model.achats.Facture;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.operations.PieceComptable;
import com.basaccount.model.ventes.FactureVente;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.FilterPredicat;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.RSNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Sat Dec 23 09:07:30 WAT 2017
 * 
 */
@Path("/piececomptable")
public class PieceComptableRSImpl
    extends AbstractGenericService<PieceComptable, Long>
    implements PieceComptableRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "PieceComptableManagerImpl", interf = PieceComptableManagerRemote.class)
    protected PieceComptableManagerRemote manager;
    
    @Manager(application = "baseaccount", name = "FactureVenteManagerImpl", interf = FactureVenteManagerRemote.class)
    protected FactureVenteManagerRemote fvmanager;
    
    @Manager(application = "baseaccount", name = "PeriodeComptableManagerImpl", interf = PeriodeComptableManagerRemote.class)
    protected PeriodeComptableManagerRemote periodemanager;
    
    @Manager(application = "baseaccount", name = "FactureManagerImpl", interf = FactureManagerRemote.class)
    protected FactureManagerRemote famanager;
    

    public PieceComptableRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<PieceComptable, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            //To change body of generated methods, choose Tools | Templates.
            meta = MetaDataUtil.getMetaData(new PieceComptable(), new HashMap<String, MetaData>(),new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(PieceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PieceComptableRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta;
    }

    @Override
    public List<PieceComptable> filter(HttpHeaders headers, int firstResult, int maxResult) {
         Gson gson =new Gson();
        //Type predType = ;
        List contraints = new ArrayList();
        if(headers.getRequestHeader("predicats")!=null&&!headers.getRequestHeader("predicats").isEmpty()){
            contraints = gson.fromJson(headers.getRequestHeader("predicats").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        } //end if(headers.getRequestHeader("predicats")!=null){     
        String searchText = null;

        String liveSearch = null;
        if(headers.getRequestHeader("search_text")!=null && !headers.getRequestHeader("search_text").isEmpty()){
            searchText = gson.fromJson(headers.getRequestHeader("search_text").get(0),String.class);
        } //end if(headers.getRequestHeader("predicats")!=null){     
        if(headers.getRequestHeader("live_search")!=null && !headers.getRequestHeader("live_search").isEmpty()){
            liveSearch = gson.fromJson(headers.getRequestHeader("live_search").get(0),String.class);
        } //end if(headers.getRequestHeader("predicats")!=null){     

//        System.out.println(AbstractGenericService.class.toString()+" === "+headers.getRequestHeader("predicats")+" === "+firstResult+" === "+maxResult+" == "+contraints);   
        RestrictionsContainer container = RestrictionsContainer.newInstance();  
        if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat filter = (FilterPredicat) obj ;
                if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
                        &&filter.getValue()!=null&&!filter.getValue().isEmpty()){
                        container = addPredicate(container,filter);
                }//end if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
            }//end  for(Object obj : contraints)
        }//end if(contraints!=null&&!contraints.isEmpty())

        if(liveSearch!=null&&!liveSearch.trim().isEmpty()){
            container.addLike("searchkeys", "%"+liveSearch);
        }else if(searchText!=null&&!searchText.trim().isEmpty()){
            container.addLike("searchkeys", "%"+searchText);
        }//end if(searchText!=null&&!searchText.trim().isEmpty()){        
        if(headers.getRequestHeader("periode")!=null && !headers.getRequestHeader("periode").isEmpty()){
            container.addEq("periode.id", gson.fromJson(headers.getRequestHeader("periode").get(0), Long.class));
        }//end if(headers.getRequestHeader("periode")!=null && !headers.getRequestHeader("periode").isEmpty()){
        if(headers.getRequestHeader("journal")!=null && !headers.getRequestHeader("journal").isEmpty()){
            container.addEq("journal.id", gson.fromJson(headers.getRequestHeader("journal").get(0),Long.class));
        }//endif(headers.getRequestHeader("journal")!=null && !headers.getRequestHeader("journal").isEmpty()){
        //List result = new ArrayList();
        return getManager().filter(container.getPredicats(), null , new HashSet<String>(), firstResult, maxResult);
    }
    
    

    @Override
    protected void processBeforeUpdate(PieceComptable entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("piece.comptable.numero.piece.empty");
        }else if(entity.getDatePiece()==null){
            throw new KerenExecption("piece.comptable.date.null");
        }else if(entity.getLibelle()==null||entity.getLibelle().trim().isEmpty()){
            throw new KerenExecption("piece.comptable.libelle.empty");
        } 
      super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(PieceComptable entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("piece.comptable.numero.piece.empty");
        }else if(entity.getDatePiece()==null){
            throw new KerenExecption("piece.comptable.date.null");
        }else if(entity.getLibelle()==null||entity.getLibelle().trim().isEmpty()){
            throw new KerenExecption("piece.comptable.libelle.empty");
        } 
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RSNumber count(HttpHeaders headers) {
         Gson gson =new Gson();
        //Type predType = ;
        List contraints = new ArrayList();
        if(headers.getRequestHeader("predicats")!=null){
            contraints = gson.fromJson(headers.getRequestHeader("predicats").get(0),new TypeToken<List<FilterPredicat>>(){}.getType());
        }//end if(headers.getRequestHeader("predicats")!=null){        
         String searchText = null;

        String liveSearch = null;
        if(headers.getRequestHeader("search_text")!=null && !headers.getRequestHeader("search_text").isEmpty()){
            searchText = gson.fromJson(headers.getRequestHeader("search_text").get(0),String.class);
        } //end if(headers.getRequestHeader("predicats")!=null){     
        if(headers.getRequestHeader("live_search")!=null && !headers.getRequestHeader("live_search").isEmpty()){
            liveSearch = gson.fromJson(headers.getRequestHeader("live_search").get(0),String.class);
        } //end if(headers.getRequestHeader("predicats")!=null){     
        RestrictionsContainer container = RestrictionsContainer.newInstance();  
         if(contraints!=null&&!contraints.isEmpty()){
            for(Object obj : contraints){
                FilterPredicat filter = (FilterPredicat) obj ;
                if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
                        &&filter.getValue()!=null&&!filter.getValue().isEmpty()){
                      container = addPredicate(container, filter);
                }//end if(filter.getFieldName()!=null&&!filter.getFieldName().trim().isEmpty()
            }//end  for(Object obj : contraints)
        }//end if(contraints!=null&&!contraints.isEmpty())
         if(liveSearch!=null&&!liveSearch.trim().isEmpty()){
            container.addLike("searchkeys", liveSearch);
        }else if(searchText!=null&&!searchText.trim().isEmpty()){
            container.addLike("searchkeys", searchText);
        }//end if(searchText!=null&&!searchText.trim().isEmpty()){       
        if(headers.getRequestHeader("periode")!=null && !headers.getRequestHeader("periode").isEmpty()){
            container.addEq("periode.id", gson.fromJson(headers.getRequestHeader("periode").get(0), Long.class));
        }//end if(headers.getRequestHeader("periode")!=null && !headers.getRequestHeader("periode").isEmpty()){
        if(headers.getRequestHeader("journal")!=null && !headers.getRequestHeader("journal").isEmpty()){
            container.addEq("journal.id", gson.fromJson(headers.getRequestHeader("journal").get(0),Long.class));
        }//endif(headers.getRequestHeader("journal")!=null && !headers.getRequestHeader("journal").isEmpty()){
        RSNumber number = new RSNumber(getManager().count(container.getPredicats()));
//        System.out.println(AbstractGenericService.class.toString()+".count === "+" == "+number.getValue());
        return number;
    } 

    @Override
    public PieceComptable valider(HttpHeaders headers, PieceComptable entity) {
         //To change body of generated methods, choose Tools | Templates.
        if(!entity.getCredit().equals(entity.getDebit())){
            throw new KerenExecption("piece.comptable.non.equilibre.error.message");
        }//end if(!entity.getCredit().equals(entity.getDebit())){
        PieceComptable data = manager.valider(entity);
        return data;
    }

    @Override
    public List<PieceComptable> priseencompte(HttpHeaders headers) {
        Gson gson = new Gson();
        if(headers.getRequestHeader("facture")==null || headers.getRequestHeader("facture").isEmpty()){
            return new ArrayList<PieceComptable>();
        }//end if(headers.getRequestHeader("facture")==null || headers.getRequestHeader("facture").isEmpty()){
        Long id = gson.fromJson(headers.getRequestHeader("facture").get(0), Long.class);
        FactureVente facture = fvmanager.find("id", id);
        PeriodeComptable periode =periodemanager.getPeriodeFromDate(facture.getDatecommande());
        if(periode==null){
            throw new KerenExecption("periode.comptable.not.found");
        } //end if(periode==null){      
        return  manager.priseencompte(id,periode);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PieceComptable> priseenccompteachat(HttpHeaders headers) {
        //To change body of generated methods, choose Tools | Templates.
       Gson gson = new Gson();
        if(headers.getRequestHeader("facture")==null || headers.getRequestHeader("facture").isEmpty()){
            return new ArrayList<PieceComptable>();
        }//end if(headers.getRequestHeader("facture")==null || headers.getRequestHeader("facture").isEmpty()){
        Long id = gson.fromJson(headers.getRequestHeader("facture").get(0), Long.class);
        Facture facture = famanager.find("id", id);
        PeriodeComptable periode =periodemanager.getPeriodeFromDate(facture.getDatecommande());
        if(periode==null){
            throw new KerenExecption("periode.comptable.not.found");
        } //end if(periode==null){      
        return  manager.priseencompteachat(id,periode);
    }
    
}
