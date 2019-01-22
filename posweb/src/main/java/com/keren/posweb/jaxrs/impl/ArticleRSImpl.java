
package com.keren.posweb.jaxrs.impl;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kerem.core.KerenExecption;
import com.kerem.core.MetaDataUtil;
import com.keren.posweb.core.ifaces.ArticleManagerRemote;
import com.keren.posweb.jaxrs.ifaces.ArticleRS;
import com.keren.posweb.model.Article;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.FilterPredicat;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.megatimgroup.generic.jax.rs.layer.impl.RSNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Tue Sep 04 17:34:18 GMT+01:00 2018
 * 
 */
@Path("/article")
public class ArticleRSImpl
    extends AbstractGenericService<Article, Long>
    implements ArticleRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "posweb", name = "ArticleManagerImpl", interf = ArticleManagerRemote.class)
    protected ArticleManagerRemote manager;

    public ArticleRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<Article, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("posweb");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new Article(), new HashMap<String, MetaData>(), new ArrayList<String>());
             MetaColumn workbtn = new MetaColumn("header", "work1", "Annotations", false, "link", null);
            workbtn.setValue("{'name':'courrier_trait_02_1',template:{'courrier':'object','quoteur':'object.destinataire','service':'object.destinataire.service'},'header':['courrier'],'label':'delai'}");
            workbtn.setStates(new String[]{"etabli"});
            workbtn.setPattern("fa fa-quora");
            meta.getHeader().add(workbtn);  
            workbtn = new MetaColumn("header", "work2", "Quotations", false, "link", null);
            workbtn.setValue("{'name':'courrier_trait_02_1',template:{'courrier':'object','quoteur':'object.destinataire','service':'object.destinataire.service'},'header':['courrier'],'label':'delai'}");
            workbtn.setStates(new String[]{"etabli"});
            workbtn.setPattern("fa fa-pencil");
            meta.getHeader().add(workbtn);  
            workbtn = new MetaColumn("header", "work3", "Classements", false, "link", null);
            workbtn.setValue("{'name':'courrier_trait_02_1',template:{'courrier':'object','quoteur':'object.destinataire','service':'object.destinataire.service'},'header':['courrier'],'label':'delai'}");
            workbtn.setStates(new String[]{"etabli"});
            workbtn.setPattern("fa fa-university");
            meta.getHeader().add(workbtn);  
        } catch (InstantiationException ex) {
            Logger.getLogger(ArticleRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ArticleRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta;
    }

    @Override
    protected void processBeforeUpdate(Article entity) {
         if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le Champ Reference Interne est obligatoire");
        }else if(entity.getIntitule()==null||entity.getIntitule().trim().isEmpty()){
            throw new KerenExecption("Le Champ Intitulé est obligatoire");
        }
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(Article entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("Le Champ Reference Interne est obligatoire");
        }else if(entity.getIntitule()==null||entity.getIntitule().trim().isEmpty()){
            throw new KerenExecption("Le Champ Intitulé est obligatoire");
        }
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
     /**
     * 
     * @param headers
     * @param firstResult
     * @param maxResult
     * @return 
     */
    @Override
    public List<Article> filter(@Context HttpHeaders headers ,int firstResult, int maxResult) {
        //To change body of generated methods, choose Tools | Templates.        
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
        if(headers.getRequestHeader("familleid")!=null && !headers.getRequestHeader("familleid").isEmpty()){
            Long familleid = gson.fromJson(headers.getRequestHeader("familleid").get(0), Long.class);
            container.addEq("famille.id", familleid);
//            System.out.println(ArticleRSImpl.class.toString()+" ============================ "+headers.getRequestHeader("familleid"));
        
        }//end if(headers.getRequestHeader("familleid")!=null && !headers.getRequestHeader("familleid").isEmpty()){
        //List result = new ArrayList();
        return getManager().filter(container.getPredicats(), null , new HashSet<String>(), firstResult, maxResult);
    }
    
    @Override
    public RSNumber count(@Context HttpHeaders headers) {
        //To change body of generated methods, choose Tools | Templates.
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
        if(headers.getRequestHeader("familleid")!=null && !headers.getRequestHeader("familleid").isEmpty()){
            Long familleid = gson.fromJson(headers.getRequestHeader("familleid").get(0), Long.class);
            container.addEq("famille.id", familleid);
        }//end if(headers.getRequestHeader("familleid")!=null && !headers.getRequestHeader("familleid").isEmpty()){
        RSNumber number = new RSNumber(getManager().count(container.getPredicats()));
//        System.out.println(AbstractGenericService.class.toString()+".count === "+" == "+number.getValue());
        return number;
    }


}
