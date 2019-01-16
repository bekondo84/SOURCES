
package com.basaccount.dao.impl.operations;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.operations.OperationBancaireDAOLocal;
import com.basaccount.dao.ifaces.operations.OperationBancaireDAORemote;
import com.basaccount.model.operations.OperationBancaire;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "OperationBancaireDAO")
public class OperationBancaireDAOImpl
    extends AbstractGenericDAO<OperationBancaire, Long>
    implements OperationBancaireDAOLocal, OperationBancaireDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public OperationBancaireDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<OperationBancaire> getManagedEntityClass() {
        return (OperationBancaire.class);
    }

}
