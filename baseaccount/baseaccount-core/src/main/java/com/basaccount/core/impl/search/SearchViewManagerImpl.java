
package com.basaccount.core.impl.search;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.search.SearchViewManagerLocal;
import com.basaccount.core.ifaces.search.SearchViewManagerRemote;
import com.basaccount.dao.ifaces.search.SearchViewDAOLocal;
import com.basaccount.model.search.SearchView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "SearchViewManager")
public class SearchViewManagerImpl
    extends AbstractGenericManager<SearchView, Long>
    implements SearchViewManagerLocal, SearchViewManagerRemote
{

    @EJB(name = "SearchViewDAO")
    protected SearchViewDAOLocal dao;

    public SearchViewManagerImpl() {
    }

    @Override
    public GenericDAO<SearchView, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
