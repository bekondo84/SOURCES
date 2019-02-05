
package com.basaccount.core.impl.search;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.search.PieceComptableViewManagerLocal;
import com.basaccount.core.ifaces.search.PieceComptableViewManagerRemote;
import com.basaccount.dao.ifaces.search.PieceComptableViewDAOLocal;
import com.basaccount.model.search.PieceComptableView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "PieceComptableViewManager")
public class PieceComptableViewManagerImpl
    extends AbstractGenericManager<PieceComptableView, Long>
    implements PieceComptableViewManagerLocal, PieceComptableViewManagerRemote
{

    @EJB(name = "PieceComptableViewDAO")
    protected PieceComptableViewDAOLocal dao;

    public PieceComptableViewManagerImpl() {
    }

    @Override
    public GenericDAO<PieceComptableView, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
