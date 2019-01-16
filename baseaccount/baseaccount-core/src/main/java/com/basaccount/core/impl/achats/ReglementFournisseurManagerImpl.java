
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.ReglementFournisseurManagerLocal;
import com.basaccount.core.ifaces.achats.ReglementFournisseurManagerRemote;
import com.basaccount.dao.ifaces.achats.ReglementFournisseurDAOLocal;
import com.basaccount.model.achats.LigneReglementFournisseur;
import com.basaccount.model.achats.ReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "ReglementFournisseurManager")
public class ReglementFournisseurManagerImpl
    extends AbstractGenericManager<ReglementFournisseur, Long>
    implements ReglementFournisseurManagerLocal, ReglementFournisseurManagerRemote
{

    @EJB(name = "ReglementFournisseurDAO")
    protected ReglementFournisseurDAOLocal dao;

    public ReglementFournisseurManagerImpl() {
    }

    @Override
    public GenericDAO<ReglementFournisseur, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<ReglementFournisseur> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<ReglementFournisseur> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<ReglementFournisseur> result = new ArrayList<ReglementFournisseur>();
        for(ReglementFournisseur data:datas){
            result.add(new ReglementFournisseur(data));
        }
        return result;
    }

    @Override
    public List<ReglementFournisseur> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReglementFournisseur find(String propertyName, Long entityID) {
        ReglementFournisseur data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        ReglementFournisseur result = new ReglementFournisseur(data);
        for(LigneReglementFournisseur ligne:data.getLignes()){
            result.getLignes().add(new LigneReglementFournisseur(ligne));
        }
        return result;
    }

    @Override
    public ReglementFournisseur delete(Long id) {
        ReglementFournisseur data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new ReglementFournisseur(data);
    }

    
}
