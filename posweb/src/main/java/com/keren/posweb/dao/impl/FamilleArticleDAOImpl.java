
package com.keren.posweb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.posweb.dao.ifaces.FamilleArticleDAOLocal;
import com.keren.posweb.dao.ifaces.FamilleArticleDAORemote;
import com.keren.posweb.model.FamilleArticle;

@Stateless(mappedName = "FamilleArticleDAO")
public class FamilleArticleDAOImpl
    extends AbstractGenericDAO<FamilleArticle, Long>
    implements FamilleArticleDAOLocal, FamilleArticleDAORemote
{

    @PersistenceContext(unitName = "posweb")
    protected EntityManager em;

    public FamilleArticleDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<FamilleArticle> getManagedEntityClass() {
        return (FamilleArticle.class);
    }

}
