
package com.basaccount.dao.impl.search;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.search.OperationBancaireViewDAOLocal;
import com.basaccount.dao.ifaces.search.OperationBancaireViewDAORemote;
import com.basaccount.model.search.OperationBancaireView;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "OperationBancaireViewDAO")
public class OperationBancaireViewDAOImpl
    extends AbstractGenericDAO<OperationBancaireView, Long>
    implements OperationBancaireViewDAOLocal, OperationBancaireViewDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public OperationBancaireViewDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<OperationBancaireView> getManagedEntityClass() {
        return (OperationBancaireView.class);
    }

}
