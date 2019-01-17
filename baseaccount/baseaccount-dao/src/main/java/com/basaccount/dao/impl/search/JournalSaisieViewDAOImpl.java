
package com.basaccount.dao.impl.search;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.search.JournalSaisieViewDAOLocal;
import com.basaccount.dao.ifaces.search.JournalSaisieViewDAORemote;
import com.basaccount.model.search.JournalSaisieView;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "JournalSaisieViewDAO")
public class JournalSaisieViewDAOImpl
    extends AbstractGenericDAO<JournalSaisieView, Long>
    implements JournalSaisieViewDAOLocal, JournalSaisieViewDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public JournalSaisieViewDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<JournalSaisieView> getManagedEntityClass() {
        return (JournalSaisieView.class);
    }

}
