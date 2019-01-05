
package com.teratech.vente.core.impl.operations;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import com.teratech.vente.core.ifaces.operations.CommandeManagerLocal;
import com.teratech.vente.core.ifaces.operations.CommandeManagerRemote;
import com.teratech.vente.dao.ifaces.operations.CommandeDAOLocal;
import com.teratech.vente.model.operations.Commande;
import com.teratech.vente.model.operations.LigneCommande;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "CommandeManager")
public class CommandeManagerImpl
    extends AbstractGenericManager<Commande, Long>
    implements CommandeManagerLocal, CommandeManagerRemote
{

    @EJB(name = "CommandeDAO")
    protected CommandeDAOLocal dao;

    public CommandeManagerImpl() {
    }

    @Override
    public GenericDAO<Commande, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

   

    @Override
    public List<Commande> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<Commande> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<Commande> result = new ArrayList<Commande>();
        for(Commande data:datas){
            result.add(new Commande(data));
        }
        return result;
    }

    @Override
    public Commande find(String propertyName, Long entityID) {
        Commande data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        Commande entity = new Commande(data);
        for(LigneCommande ligne:data.getLignes()){
            entity.getLignes().add(new LigneCommande(ligne));
        }//end for(LigneCommande ligne:data.getLignes()){
        return entity;
    }

    @Override
    public Commande delete(Long id) {
        Commande data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new Commande(data);
    }
    
    

}
