
package com.basaccount.dao.impl.ventes;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.ventes.LigneReglementClientDAOLocal;
import com.basaccount.dao.ifaces.ventes.LigneReglementClientDAORemote;
import com.basaccount.model.ventes.LigneReglementClient;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "LigneReglementClientDAO")
public class LigneReglementClientDAOImpl
    extends AbstractGenericDAO<LigneReglementClient, Long>
    implements LigneReglementClientDAOLocal, LigneReglementClientDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public LigneReglementClientDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<LigneReglementClient> getManagedEntityClass() {
        return (LigneReglementClient.class);
    }

}
