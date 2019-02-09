
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.EcheanceReglementManagerLocal;
import com.basaccount.core.ifaces.achats.EcheanceReglementManagerRemote;
import com.basaccount.dao.ifaces.achats.EcheanceReglementDAOLocal;
import com.basaccount.model.achats.EcheanceReglement;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "EcheanceReglementManager")
public class EcheanceReglementManagerImpl
    extends AbstractGenericManager<EcheanceReglement, Long>
    implements EcheanceReglementManagerLocal, EcheanceReglementManagerRemote
{

    @EJB(name = "EcheanceReglementDAO")
    protected EcheanceReglementDAOLocal dao;

    public EcheanceReglementManagerImpl() {
    }

    @Override
    public GenericDAO<EcheanceReglement, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
