
package com.teratech.vente.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.vente.dao.ifaces.base.ArticleDAOLocal;
import com.teratech.vente.dao.ifaces.base.ArticleDAORemote;
import com.teratech.vente.model.base.Article;

@Stateless(mappedName = "ArticleDAO")
public class ArticleDAOImpl
    extends AbstractGenericDAO<Article, Long>
    implements ArticleDAOLocal, ArticleDAORemote
{

    @PersistenceContext(unitName = "teratechvente")
    protected EntityManager em;

    public ArticleDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Article> getManagedEntityClass() {
        return (Article.class);
    }

    @Override
    public void processAfterUpdate(Article entity) {
//        String srcname = FileHelper.getTemporalDirectory().toString()+"/"+entity.getDoc();
//        String ciblename = FileHelper.getStaticDirectory()+"/ventes/"+entity.getDoc();
//        File srcFile = new File(srcname);
//        if(srcFile.exists() && srcFile.isFile()){
//            try {
//                FileHelper.moveFile(srcFile, new File(ciblename));
//            } catch (IOException ex) {
//               throw new RuntimeException(ex);
//            }
//        }//end if(srcFile.exists() && srcFile.isFile()){
        super.processAfterUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processAfterSave(Article entity) {
//         String srcname = FileHelper.getTemporalDirectory().toString()+"/"+entity.getDoc();
//        String ciblename = FileHelper.getStaticDirectory()+"/ventes/"+entity.getDoc();
//        File srcFile = new File(srcname);
//        if(srcFile.exists() && srcFile.isFile()){
//            try {
//                FileHelper.moveFile(srcFile, new File(ciblename));
//            } catch (IOException ex) {
//               throw new RuntimeException(ex);
//            }
//        }//end if(srcFile.exists() && srcFile.isFile()){
        super.processAfterSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
