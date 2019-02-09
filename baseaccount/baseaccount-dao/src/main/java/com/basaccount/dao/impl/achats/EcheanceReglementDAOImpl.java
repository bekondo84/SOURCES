
package com.basaccount.dao.impl.achats;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.achats.EcheanceReglementDAOLocal;
import com.basaccount.dao.ifaces.achats.EcheanceReglementDAORemote;
import com.basaccount.model.achats.EcheanceReglement;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "EcheanceReglementDAO")
public class EcheanceReglementDAOImpl
    extends AbstractGenericDAO<EcheanceReglement, Long>
    implements EcheanceReglementDAOLocal, EcheanceReglementDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public EcheanceReglementDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<EcheanceReglement> getManagedEntityClass() {
        return (EcheanceReglement.class);
    }

}
