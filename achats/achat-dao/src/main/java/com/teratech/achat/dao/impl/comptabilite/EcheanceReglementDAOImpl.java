
package com.teratech.achat.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.achat.dao.ifaces.comptabilite.EcheanceReglementDAOLocal;
import com.teratech.achat.dao.ifaces.comptabilite.EcheanceReglementDAORemote;
import com.teratech.achat.model.comptabilite.EcheanceReglement;

@Stateless(mappedName = "EcheanceReglementDAO")
public class EcheanceReglementDAOImpl
    extends AbstractGenericDAO<EcheanceReglement, Long>
    implements EcheanceReglementDAOLocal, EcheanceReglementDAORemote
{

    @PersistenceContext(unitName = "teratechachat")
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
