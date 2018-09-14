
package com.keren.core.impl.formations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.core.ifaces.formations.LigneBesionFormationManagerLocal;
import com.keren.core.ifaces.formations.LigneBesionFormationManagerRemote;
import com.keren.dao.ifaces.formations.LigneBesionFormationDAOLocal;
import com.keren.model.formations.LigneBesionFormation;

@TransactionAttribute
@Stateless(mappedName = "LigneBesionFormationManager")
public class LigneBesionFormationManagerImpl
    extends AbstractGenericManager<LigneBesionFormation, Long>
    implements LigneBesionFormationManagerLocal, LigneBesionFormationManagerRemote
{

    @EJB(name = "LigneBesionFormationDAO")
    protected LigneBesionFormationDAOLocal dao;

    public LigneBesionFormationManagerImpl() {
    }

    @Override
    public GenericDAO<LigneBesionFormation, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
