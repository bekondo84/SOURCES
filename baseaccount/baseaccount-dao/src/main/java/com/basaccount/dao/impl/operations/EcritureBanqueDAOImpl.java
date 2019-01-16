
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.EcritureBanqueDAOLocal;
import com.basaccount.dao.ifaces.operations.EcritureBanqueDAORemote;
import com.basaccount.model.operations.EcritureBanque;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "EcritureBanqueDAO")
public class EcritureBanqueDAOImpl
    extends AbstractGenericDAO<EcritureBanque, Long>
    implements EcritureBanqueDAOLocal, EcritureBanqueDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EcritureBanqueDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<EcritureBanque> getManagedEntityClass() {
        return (EcritureBanque.class);
    }

}
