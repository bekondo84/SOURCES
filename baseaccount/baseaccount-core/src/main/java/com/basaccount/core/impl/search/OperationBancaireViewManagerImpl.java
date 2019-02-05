
package com.basaccount.core.impl.search;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.search.OperationBancaireViewManagerLocal;
import com.basaccount.core.ifaces.search.OperationBancaireViewManagerRemote;
import com.basaccount.dao.ifaces.search.OperationBancaireViewDAOLocal;
import com.basaccount.model.search.OperationBancaireView;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "OperationBancaireViewManager")
public class OperationBancaireViewManagerImpl
    extends AbstractGenericManager<OperationBancaireView, Long>
    implements OperationBancaireViewManagerLocal, OperationBancaireViewManagerRemote
{

    @EJB(name = "OperationBancaireViewDAO")
    protected OperationBancaireViewDAOLocal dao;

    public OperationBancaireViewManagerImpl() {
    }

    @Override
    public GenericDAO<OperationBancaireView, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
