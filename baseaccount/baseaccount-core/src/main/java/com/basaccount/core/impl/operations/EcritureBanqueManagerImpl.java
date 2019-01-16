
package com.basaccount.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.operations.EcritureBanqueManagerLocal;
import com.basaccount.core.ifaces.operations.EcritureBanqueManagerRemote;
import com.basaccount.dao.ifaces.operations.EcritureBanqueDAOLocal;
import com.basaccount.model.operations.EcritureBanque;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "EcritureBanqueManager")
public class EcritureBanqueManagerImpl
    extends AbstractGenericManager<EcritureBanque, Long>
    implements EcritureBanqueManagerLocal, EcritureBanqueManagerRemote
{

    @EJB(name = "EcritureBanqueDAO")
    protected EcritureBanqueDAOLocal dao;

    public EcritureBanqueManagerImpl() {
    }

    @Override
    public GenericDAO<EcritureBanque, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
