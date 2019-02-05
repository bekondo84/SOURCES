
package com.basaccount.dao.impl.search;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.basaccount.dao.ifaces.search.PieceComptableViewDAOLocal;
import com.basaccount.dao.ifaces.search.PieceComptableViewDAORemote;
import com.basaccount.model.search.PieceComptableView;
import com.bekosoftware.genericdaolayer.dao.impl.AbstractGenericDAO;

@Stateless(mappedName = "PieceComptableViewDAO")
public class PieceComptableViewDAOImpl
    extends AbstractGenericDAO<PieceComptableView, Long>
    implements PieceComptableViewDAOLocal, PieceComptableViewDAORemote
{

    @PersistenceContext(unitName = "keren")
    protected EntityManager em;

    public PieceComptableViewDAOImpl() {
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public Class<PieceComptableView> getManagedEntityClass() {
        return (PieceComptableView.class);
    }

}
