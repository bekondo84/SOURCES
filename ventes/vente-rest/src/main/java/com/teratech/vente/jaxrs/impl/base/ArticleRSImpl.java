
package com.teratech.vente.jaxrs.impl.base;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.kerem.core.MetaDataUtil;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaColumn;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import com.teratech.vente.core.ifaces.base.ArticleManagerRemote;
import com.teratech.vente.jaxrs.ifaces.base.ArticleRS;
import com.teratech.vente.model.base.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Jan 04 08:13:39 WAT 2019
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
    @Manager(application = "teratechvente", name = "ArticleManagerImpl", interf = ArticleManagerRemote.class)
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
        return ("teratechvente");
    }

     @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new Article(), new HashMap<String, MetaData>(), new ArrayList<String>()); //To change body of generated methods, choose Tools | Templates.
            MetaColumn workbtn = new MetaColumn("button", "work1", "Emplacements de stockage", false, "link", null);
            workbtn.setValue("{'name':'teratech_vente_str_1_1',template:{'article':'object'},'header':['article']}");
//            workbtn.setStates(new String[]{"etabli"});
            meta.getHeader().add(workbtn);
            workbtn = new MetaColumn("header", "work1", "Publier sur le portail", false, "link", null);
            workbtn.setValue("{'name':'teratech_vente_str_1_1',template:{'article':'object'},'header':['article'],'label':'puvente'}");
            workbtn.setPattern("fa fa-envelope");workbtn.setLabel("puvente");
//            workbtn.setStates(new String[]{"etabli"});
            meta.getHeader().add(workbtn);
            workbtn = new MetaColumn("header", "work1", "Stock disponible", false, "link", null);
            workbtn.setValue("{'name':'teratech_vente_str_1_1',template:{'article':'object'},'header':['article'],'label':'puvente'}");
            workbtn.setPattern("fa fa-envelope");workbtn.setLabel("puvente");
//            workbtn.setStates(new String[]{"etabli"});
            meta.getHeader().add(workbtn);
            workbtn = new MetaColumn("header", "work1", "Statistiques", false, "link", null);
            workbtn.setValue("{'name':'teratech_vente_str_1_1',template:{'article':'object'},'header':['article'],'label':'puvente'}");
            workbtn.setPattern("fa fa-envelope");workbtn.setLabel("puvente");
//            workbtn.setStates(new String[]{"etabli"});
            meta.getHeader().add(workbtn);
            workbtn = new MetaColumn("header", "work1", "Points de ventes", false, "link", null);
            workbtn.setValue("{'name':'teratech_vente_str_1_1',template:{'article':'object'},'header':['article'],'label':'puvente'}");
            workbtn.setPattern("fa fa-envelope");workbtn.setLabel("puvente");
//            workbtn.setStates(new String[]{"etabli"});
            meta.getHeader().add(workbtn);
            workbtn = new MetaColumn("header", "work1", "Points de ventes", false, "link", null);
            workbtn.setValue("{'name':'teratech_vente_str_1_1',template:{'article':'object'},'header':['article'],'label':'puvente'}");
            workbtn.setPattern("fa fa-envelope");workbtn.setLabel("puvente");
//            workbtn.setStates(new String[]{"etabli"});
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
        for(int i=0;i<entity.getStockages().size();i++){
            if(entity.getStockages().get(i).getId()<=0){
                entity.getStockages().get(i).setId(-1);
            }//end if(entity.getStockages().get(i).getId()<=0){
        }//end for(int i=0;i<=entity.getStockages().size();i++){
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processBeforeSave(Article entity) {
        for(int i=0;i<entity.getStockages().size();i++){
            if(entity.getStockages().get(i).getId()<=0){
                entity.getStockages().get(i).setId(-1);
            }//end if(entity.getStockages().get(i).getId()<=0){
        }//end for(int i=0;i<=entity.getStockages().size();i++){
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
}
