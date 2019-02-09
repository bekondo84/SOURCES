
package com.basaccount.core.impl.ventes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.ventes.LigneReglementClientManagerLocal;
import com.basaccount.core.ifaces.ventes.LigneReglementClientManagerRemote;
import com.basaccount.dao.ifaces.ventes.LigneReglementClientDAOLocal;
import com.basaccount.model.ventes.LigneReglementClient;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "LigneReglementClientManager")
public class LigneReglementClientManagerImpl
    extends AbstractGenericManager<LigneReglementClient, Long>
    implements LigneReglementClientManagerLocal, LigneReglementClientManagerRemote
{

    @EJB(name = "LigneReglementClientDAO")
    protected LigneReglementClientDAOLocal dao;

    public LigneReglementClientManagerImpl() {
    }

    @Override
    public GenericDAO<LigneReglementClient, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
