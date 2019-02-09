
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerLocal;
import com.basaccount.core.ifaces.comptabilite.PeriodeComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.ExerciceComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.PeriodeComptableDAOLocal;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "PeriodeComptableManager")
public class PeriodeComptableManagerImpl
    extends AbstractGenericManager<PeriodeComptable, Long>
    implements PeriodeComptableManagerLocal, PeriodeComptableManagerRemote
{

    @EJB(name = "PeriodeComptableDAO")
    protected PeriodeComptableDAOLocal dao;
    
    @EJB(name = "ExerciceComptableDAO")
    protected ExerciceComptableDAOLocal exodao;

    public PeriodeComptableManagerImpl() {
    }

    @Override
    public GenericDAO<PeriodeComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<PeriodeComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<PeriodeComptable> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<PeriodeComptable> result = new ArrayList<PeriodeComptable>();
        for(PeriodeComptable data:datas){
            result.add(new PeriodeComptable(data));
        }
        return result;
    }

    @Override
    public PeriodeComptable find(String propertyName, Long entityID) {
        PeriodeComptable data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        PeriodeComptable result = new PeriodeComptable(data);
        return result;
    }

    @Override
    public PeriodeComptable delete(Long id) {
        PeriodeComptable data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new PeriodeComptable(data);
    }

    @Override
    public List<ExerciceComptable> getExercices() {
         //To change body of generated methods, choose Tools | Templates.
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        List<ExerciceComptable> datas = exodao.filter(container.getPredicats(), null, null, 0, -1);
        List<ExerciceComptable> result = new ArrayList<ExerciceComptable>();
        for(ExerciceComptable data : datas){
            ExerciceComptable entity = new ExerciceComptable(data);
            for(PeriodeComptable periode:data.getPeriodes()){
                entity.getPeriodes().add(new PeriodeComptable(periode));
            }//end for(PeriodeComptable periode:data.getPeriodes()){
            result.add(entity);
        }//end for(ExerciceComptable data : datas){
        return result;
    }
    
    
    @Override
    public PeriodeComptable getPeriodeFromDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       RestrictionsContainer container = RestrictionsContainer.newInstance();
       container.addLe("debut", date);
       container.addGe("fin", date);
       List<PeriodeComptable> periodes = dao.filter(container.getPredicats(), null, null, 0, -1);
       if(periodes!=null && !periodes.isEmpty()){
           return periodes.get(0);
       }
       return null;
    }

}
