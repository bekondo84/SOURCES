
package com.basaccount.jaxrs.impl.operations;

import javax.ws.rs.Path;
import com.basaccount.core.ifaces.operations.OperationBancaireManagerRemote;
import com.basaccount.jaxrs.ifaces.operations.OperationBancaireRS;
import com.basaccount.model.operations.OperationBancaire;
import com.basaccount.model.operations.PieceComptable;
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

 * @since Wed Jan 16 11:53:29 WAT 2019
 * 
 */
@Path("/operationbancaire")
public class OperationBancaireRSImpl
    extends AbstractGenericService<OperationBancaire, Long>
    implements OperationBancaireRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "baseaccount", name = "OperationBancaireManagerImpl", interf = OperationBancaireManagerRemote.class)
    protected OperationBancaireManagerRemote manager;

    public OperationBancaireRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<OperationBancaire, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("baseaccount");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta =null;
        try {
            meta = MetaDataUtil.getMetaData(new OperationBancaire(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(OperationBancaireRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(OperationBancaireRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
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
    public List<OperationBancaire> filter(HttpHeaders headers, int firstResult, int maxResult) {
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
    public OperationBancaire valider(HttpHeaders headers, OperationBancaire entity) {
        //To change body of generated methods, choose Tools | Templates.
         if(!entity.getCredit().equals(entity.getDebit())){
            throw new KerenExecption("operation.bancaire.non.equilibre.error.message");
        }//end if(!entity.getCredit().equals(entity.getDebit())){
        OperationBancaire data = manager.valider(entity);
        return data;
    }
    
    

}
