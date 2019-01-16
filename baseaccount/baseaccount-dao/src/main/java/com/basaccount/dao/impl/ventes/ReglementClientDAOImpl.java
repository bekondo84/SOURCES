
package com.basaccount.dao.impl.ventes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.ventes.ReglementClientDAOLocal;
import com.basaccount.dao.ifaces.ventes.ReglementClientDAORemote;
import com.basaccount.model.ventes.ReglementClient;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ReglementClientDAO")
public class ReglementClientDAOImpl
    extends AbstractGenericDAO<ReglementClient, Long>
    implements ReglementClientDAOLocal, ReglementClientDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ReglementClientDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ReglementClient> getManagedEntityClass() {
        return (ReglementClient.class);
    }

}
