
package com.keren.posweb.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.posweb.core.ifaces.CaissierManagerLocal;
import com.keren.posweb.core.ifaces.CaissierManagerRemote;
import com.keren.posweb.dao.ifaces.CaissierDAOLocal;
import com.keren.posweb.model.Caissier;
import com.keren.posweb.model.PointVente;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "CaissierManager")
public class CaissierManagerImpl
    extends AbstractGenericManager<Caissier, Long>
    implements CaissierManagerLocal, CaissierManagerRemote
{

    @EJB(name = "CaissierDAO")
    protected CaissierDAOLocal dao;

    public CaissierManagerImpl() {
    }

    @Override
    public GenericDAO<Caissier, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<Caissier> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Caissier> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<Caissier> result = new ArrayList<Caissier>();
        for(Caissier data:datas){
            result.add(new Caissier(data));
        }
        return result;
    }

    @Override
    public Caissier find(String propertyName, Long entityID) {
        Caissier data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        Caissier result = new Caissier(data);
        for(PointVente pos : data.getPointsofsales()){
            result.getPointsofsales().add(new PointVente(pos));
        }
        return result;
    }

    @Override
    public Caissier delete(Long id) {
        Caissier data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new Caissier(data);
    }
    
    

}
