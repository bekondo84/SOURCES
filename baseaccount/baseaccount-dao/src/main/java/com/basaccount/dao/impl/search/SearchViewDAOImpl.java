
package com.basaccount.dao.impl.search;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.search.SearchViewDAOLocal;
import com.basaccount.dao.ifaces.search.SearchViewDAORemote;
import com.basaccount.model.search.SearchView;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "SearchViewDAO")
public class SearchViewDAOImpl
    extends AbstractGenericDAO<SearchView, Long>
    implements SearchViewDAOLocal, SearchViewDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public SearchViewDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<SearchView> getManagedEntityClass() {
        return (SearchView.class);
    }

}
