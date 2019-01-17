
package com.basaccount.dao.impl.comptabilite;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.comptabilite.PeriodeComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.PeriodeComptableDAORemote;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "PeriodeComptableDAO")
public class PeriodeComptableDAOImpl
    extends AbstractGenericDAO<PeriodeComptable, Long>
    implements PeriodeComptableDAOLocal, PeriodeComptableDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public PeriodeComptableDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<PeriodeComptable> getManagedEntityClass() {
        return (PeriodeComptable.class);
    }

}
