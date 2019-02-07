
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.JournalComptableManagerLocal;
import com.basaccount.core.ifaces.comptabilite.JournalComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.ExerciceComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.JournalComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerem.commons.DateHelper;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@TransactionAttribute
@Stateless(mappedName = "JournalComptableManager")
public class JournalComptableManagerImpl
    extends AbstractGenericManager<JournalComptable, Long>
    implements JournalComptableManagerLocal, JournalComptableManagerRemote
{

    @EJB(name = "JournalComptableDAO")
    protected JournalComptableDAOLocal dao;
    
    @EJB(name = "ExerciceComptableDAO")
    protected ExerciceComptableDAOLocal exercicedao;    
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;

    public JournalComptableManagerImpl() {
    }

    @Override
    public GenericDAO<JournalComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public void processBeforeSave(JournalComptable entity) {
        //Creation des journaux de saisie
//        ExerciceComptable exercice = exercicedao.getOpenExercice();
//        if(exercice==null){
//            RuntimeException excep = new RuntimeException("Aucun exercice comptable disponible");
//            throw new WebApplicationException(excep,Response.Status.NOT_MODIFIED);
//        }
//        String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
//        Date begin = exercice.getDebut();
//        while(DateHelper.convertToString(begin, "yyyy-MM-dd")
//                .compareTo(DateHelper.convertToString(exercice.getFin(), "yyyy-MM-dd"))<=0){
//            String code = shortMonths[begin.getMonth()]+"."+DateHelper.convertToString(begin, "yy");
//            JournalSaisie saisie = new JournalSaisie(code, exercice, DateHelper.getFirstDayOfMonth(begin)
//                    , DateHelper.getLastDayOfMonth(begin));
//            saisie.setJournal(entity);
//            journalsaisiedao.save(saisie);
//            begin = DateHelper.nextMonth(begin);
//        }//end while(DateHelper.convertToString(begin, "yyyy-MM-dd")
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<JournalComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<JournalComptable> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<JournalComptable> result = new ArrayList<JournalComptable>();
        for(JournalComptable data:datas){
            result.add(new JournalComptable(data));
        }
        return result;
    }

    @Override
    public JournalComptable find(String propertyName, Long entityID) {
        JournalComptable data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        return new JournalComptable(data);
    }

    @Override
    public JournalComptable delete(Long id) {
        JournalComptable data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new JournalComptable(data);
    }
    
    

}
