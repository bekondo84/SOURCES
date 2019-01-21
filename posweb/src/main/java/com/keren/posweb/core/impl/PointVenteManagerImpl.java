
package com.keren.posweb.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.posweb.core.ifaces.PointVenteManagerLocal;
import com.keren.posweb.core.ifaces.PointVenteManagerRemote;
import com.keren.posweb.dao.ifaces.PointVenteDAOLocal;
import com.keren.posweb.model.Caissier;
import com.keren.posweb.model.PointVente;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "PointVenteManager")
public class PointVenteManagerImpl
    extends AbstractGenericManager<PointVente, Long>
    implements PointVenteManagerLocal, PointVenteManagerRemote
{

    @EJB(name = "PointVenteDAO")
    protected PointVenteDAOLocal dao;

    public PointVenteManagerImpl() {
    }

    @Override
    public GenericDAO<PointVente, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<PointVente> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<PointVente>  datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<PointVente>  result =new ArrayList<PointVente>();
        for(PointVente pos:datas){
            result.add(new PointVente(pos));
        }
        return result;
    }

   

    @Override
    public PointVente find(String propertyName, Long entityID) {
        PointVente data= super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        PointVente result = new PointVente(data);
        for(Caissier cash:data.getCashiers()){
            result.getCashiers().add(new Caissier(cash));
        }
        return result;
    }

    @Override
    public PointVente delete(Long id) {
        PointVente data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new PointVente(data);
    }
    
    

}
