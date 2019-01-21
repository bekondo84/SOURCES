
package com.keren.posweb.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.keren.posweb.dao.ifaces.CaissierDAOLocal;
import com.keren.posweb.dao.ifaces.CaissierDAORemote;
import com.keren.posweb.model.Caissier;

@Stateless(mappedName = "CaissierDAO")
public class CaissierDAOImpl
    extends AbstractGenericDAO<Caissier, Long>
    implements CaissierDAOLocal, CaissierDAORemote
{

    @PersistenceContext(unitName = "posweb")
    protected EntityManager em;

    public CaissierDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Caissier> getManagedEntityClass() {
        return (Caissier.class);
    }

}
