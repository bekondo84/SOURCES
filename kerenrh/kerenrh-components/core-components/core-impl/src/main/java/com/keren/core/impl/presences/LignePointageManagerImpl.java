
package com.keren.core.impl.presences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.core.ifaces.presences.LignePointageManagerLocal;
import com.keren.core.ifaces.presences.LignePointageManagerRemote;
import com.keren.dao.ifaces.presences.LignePointageDAOLocal;
import com.keren.model.presences.LignePointage;
import com.megatim.common.annotations.OrderType;

@TransactionAttribute
@Stateless(mappedName = "LignePointageManager")
public class LignePointageManagerImpl
    extends AbstractGenericManager<LignePointage, Long>
    implements LignePointageManagerLocal, LignePointageManagerRemote
{

    @EJB(name = "LignePointageDAO")
    protected LignePointageDAOLocal dao;

    public LignePointageManagerImpl() {
    }

    @Override
    public GenericDAO<LignePointage, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }
    
    @Override
    public LignePointage delete(Long id) {

        LignePointage data = super.find("id", id);
        LignePointage result = new LignePointage(data);

        //on supprime
        super.delete(id);

        return result;
    }
    
    @Override
    public List<LignePointage> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties,
                    int firstResult, int maxResult) {

            // TODO Auto-generated method stub
            RestrictionsContainer container = RestrictionsContainer.newInstance();
            container.addEq("pointage.state", "confirmer");
            //container.addEq("absent", Boolean.TRUE);
            container.addEq("state", "etabli");
            predicats.addAll(container.getPredicats());

            List<LignePointage> datas = super.filter(predicats, orders, properties, firstResult, maxResult);
            List<LignePointage> results = new ArrayList<LignePointage>();

            for(LignePointage data:datas){

                if(data.getAbsent() || data.getAbsencepaye()){
                    results.add(new LignePointage(data));
                }
            }//end for(LignePointage data:datas){
            return results;
    }

    @Override
    public LignePointage find(String propertyName, Long entityID) {
            // TODO Auto-generated method stub
            LignePointage data = super.find(propertyName, entityID);
            LignePointage result = new LignePointage(data);
            return result;
    }

    @Override
    public List<LignePointage> findAll() {
            // TODO Auto-generated method stub		
            List<LignePointage> datas = super.findAll();
            List<LignePointage> results = new ArrayList<LignePointage>();
            for(LignePointage data:datas){
                    results.add(new LignePointage(data));
            }//end for(LignePointage data:datas){
            return results;
    }

    @Override
    public LignePointage justifie(LignePointage entity) {

        // TODO Auto-generated method stub
        if(entity.getState().equalsIgnoreCase("etabli")){
                entity.setState("justifier");
                entity = dao.update(entity.getId(), entity);
        }//end if(entity.getState().equalsIgnoreCase("etabli")){

        LignePointage result = new LignePointage(entity);
        return result;
    }

    @Override
    public LignePointage nonjustifie(LignePointage entity) {

        // TODO Auto-generated method stub
        if(entity.getState().equalsIgnoreCase("etabli")){
                entity.setState("nonjustifier");
                entity = dao.update(entity.getId(), entity);
        }//end if(entity.getState().equalsIgnoreCase("etabli")){

        LignePointage result = new LignePointage(entity);
        return result;
    }
        
}
