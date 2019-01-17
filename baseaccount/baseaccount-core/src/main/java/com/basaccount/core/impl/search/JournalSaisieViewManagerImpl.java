
package com.basaccount.core.impl.search;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.search.JournalSaisieViewManagerLocal;
import com.basaccount.core.ifaces.search.JournalSaisieViewManagerRemote;
import com.basaccount.dao.ifaces.search.JournalSaisieViewDAOLocal;
import com.basaccount.model.search.JournalSaisieView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "JournalSaisieViewManager")
public class JournalSaisieViewManagerImpl
    extends AbstractGenericManager<JournalSaisieView, Long>
    implements JournalSaisieViewManagerLocal, JournalSaisieViewManagerRemote
{

    @EJB(name = "JournalSaisieViewDAO")
    protected JournalSaisieViewDAOLocal dao;

    public JournalSaisieViewManagerImpl() {
    }

    @Override
    public GenericDAO<JournalSaisieView, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
