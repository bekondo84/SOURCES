
package com.keren.core.impl.structures;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.core.ifaces.structures.RegionManagerLocal;
import com.keren.core.ifaces.structures.RegionManagerRemote;
import com.keren.dao.ifaces.structures.RegionDAOLocal;
import com.keren.model.structures.Region;

@TransactionAttribute
@Stateless(mappedName = "RegionManager")
public class RegionManagerImpl
    extends AbstractGenericManager<Region, Long>
    implements RegionManagerLocal, RegionManagerRemote
{

    @EJB(name = "RegionDAO")
    protected RegionDAOLocal dao;

    public RegionManagerImpl() {
    }

    @Override
    public GenericDAO<Region, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
