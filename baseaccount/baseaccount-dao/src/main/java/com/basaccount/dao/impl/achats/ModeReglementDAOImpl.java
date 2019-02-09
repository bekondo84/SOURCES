
package com.basaccount.dao.impl.achats;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.achats.ModeReglementDAOLocal;
import com.basaccount.dao.ifaces.achats.ModeReglementDAORemote;
import com.basaccount.model.achats.ModeReglement;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "ModeReglementDAO")
public class ModeReglementDAOImpl
    extends AbstractGenericDAO<ModeReglement, Long>
    implements ModeReglementDAOLocal, ModeReglementDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public ModeReglementDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<ModeReglement> getManagedEntityClass() {
        return (ModeReglement.class);
    }

}
