
package com.basaccount.core.impl.ventes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.ventes.ReglementClientManagerLocal;
import com.basaccount.core.ifaces.ventes.ReglementClientManagerRemote;
import com.basaccount.dao.ifaces.ventes.ReglementClientDAOLocal;
import com.basaccount.model.ventes.ReglementClient;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "ReglementClientManager")
public class ReglementClientManagerImpl
    extends AbstractGenericManager<ReglementClient, Long>
    implements ReglementClientManagerLocal, ReglementClientManagerRemote
{

    @EJB(name = "ReglementClientDAO")
    protected ReglementClientDAOLocal dao;

    public ReglementClientManagerImpl() {
    }

    @Override
    public GenericDAO<ReglementClient, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
