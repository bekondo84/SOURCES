
package com.keren.core.impl.employes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.core.ifaces.employes.EchelonManagerLocal;
import com.keren.core.ifaces.employes.EchelonManagerRemote;
import com.keren.dao.ifaces.employes.EchelonDAOLocal;
import com.keren.model.employes.Echelon;

@TransactionAttribute
@Stateless(mappedName = "EchelonManager")
public class EchelonManagerImpl
    extends AbstractGenericManager<Echelon, Long>
    implements EchelonManagerLocal, EchelonManagerRemote
{

    @EJB(name = "EchelonDAO")
    protected EchelonDAOLocal dao;

    public EchelonManagerImpl() {
    }

    @Override
    public GenericDAO<Echelon, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
