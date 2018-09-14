
package com.teratech.achat.dao.impl.base;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;
import com.teratech.achat.dao.ifaces.base.DeviseDAOLocal;
import com.teratech.achat.dao.ifaces.base.DeviseDAORemote;
import com.teratech.achat.model.base.Devise;

@Stateless(mappedName = "DeviseDAO")
public class DeviseDAOImpl
    extends AbstractGenericDAO<Devise, Long>
    implements DeviseDAOLocal, DeviseDAORemote
{

    @PersistenceContext(unitName = "teratechachat")
    protected EntityManager em;

    public DeviseDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<Devise> getManagedEntityClass() {
        return (Devise.class);
    }

}
