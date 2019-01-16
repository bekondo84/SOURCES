
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.OperationBancaireManagerLocal;
import com.basaccount.core.ifaces.operations.OperationBancaireManagerRemote;
import com.basaccount.dao.ifaces.operations.OperationBancaireDAOLocal;
import com.basaccount.model.operations.OperationBancaire;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "OperationBancaireManager")
public class OperationBancaireManagerImpl
    extends AbstractGenericManager<OperationBancaire, Long>
    implements OperationBancaireManagerLocal, OperationBancaireManagerRemote
{

    @EJB(name = "OperationBancaireDAO")
    protected OperationBancaireDAOLocal dao;

    public OperationBancaireManagerImpl() {
    }

    @Override
    public GenericDAO<OperationBancaire, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
