
package com.teratech.vente.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.vente.core.ifaces.base.FamilleArticleManagerLocal;
import com.teratech.vente.core.ifaces.base.FamilleArticleManagerRemote;
import com.teratech.vente.dao.ifaces.base.FamilleArticleDAOLocal;
import com.teratech.vente.model.base.FamilleArticle;

@TransactionAttribute
@Stateless(mappedName = "FamilleArticleManager")
public class FamilleArticleManagerImpl
    extends AbstractGenericManager<FamilleArticle, Long>
    implements FamilleArticleManagerLocal, FamilleArticleManagerRemote
{

    @EJB(name = "FamilleArticleDAO")
    protected FamilleArticleDAOLocal dao;

    public FamilleArticleManagerImpl() {
    }

    @Override
    public GenericDAO<FamilleArticle, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
