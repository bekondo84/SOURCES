
package com.teratech.vente.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import com.teratech.vente.core.ifaces.operations.DevisManagerLocal;
import com.teratech.vente.core.ifaces.operations.DevisManagerRemote;
import com.teratech.vente.dao.ifaces.operations.DevisDAOLocal;
import com.teratech.vente.model.operations.Devis;
import com.teratech.vente.model.operations.LigneDevis;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "DevisManager")
public class DevisManagerImpl
    extends AbstractGenericManager<Devis, Long>
    implements DevisManagerLocal, DevisManagerRemote
{

    @EJB(name = "DevisDAO")
    protected DevisDAOLocal dao;

    public DevisManagerImpl() {
    }

    @Override
    public GenericDAO<Devis, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<Devis> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Devis> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<Devis> result = new ArrayList<Devis>();
        for(Devis data:datas){
            result.add(new Devis(data));
        }
        return result;
    }

    @Override
    public Devis find(String propertyName, Long entityID) {
        Devis data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        Devis entity = new Devis(data);
        for(LigneDevis ligne:data.getLignes()){
            entity.getLignes().add(new LigneDevis(ligne));
        }
        return entity;
    }

    @Override
    public Devis delete(Long id) {
        Devis data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new Devis(data);
    }
    
    

}
