
package com.basaccount.core.impl.comptabilite;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.comptabilite.ExerciceComptableManagerLocal;
import com.basaccount.core.ifaces.comptabilite.ExerciceComptableManagerRemote;
import com.basaccount.dao.ifaces.comptabilite.ExerciceComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.JournalComptableDAOLocal;
import com.basaccount.dao.ifaces.comptabilite.PeriodeComptableDAOLocal;
import com.basaccount.dao.ifaces.operations.JournalSaisieDAOLocal;
import com.basaccount.model.comptabilite.ExerciceComptable;
import com.basaccount.model.comptabilite.JournalComptable;
import com.basaccount.model.comptabilite.PeriodeComptable;
import com.basaccount.model.operations.JournalSaisie;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerem.commons.DateHelper;
import com.megatim.common.annotations.OrderType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "ExerciceComptableManager")
public class ExerciceComptableManagerImpl
    extends AbstractGenericManager<ExerciceComptable, Long>
    implements ExerciceComptableManagerLocal, ExerciceComptableManagerRemote
{

    @EJB(name = "ExerciceComptableDAO")
    protected ExerciceComptableDAOLocal dao;
    
    @EJB(name = "JournalSaisieDAO")
    protected JournalSaisieDAOLocal journalsaisiedao;
    
    @EJB(name = "JournalComptableDAO")
    protected JournalComptableDAOLocal journaldao;
    
    @EJB(name = "PeriodeComptableDAO")
    protected PeriodeComptableDAOLocal periodedao;

    public ExerciceComptableManagerImpl() {
    }

    @Override
    public GenericDAO<ExerciceComptable, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public void processBeforeSave(ExerciceComptable entity) {
        entity.setState("etabli");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void processAfterSave(ExerciceComptable entity) {
         //To change body of generated methods, choose Tools | Templates.
        //L'exercice comptable tient sur 12 mois
//        entity = dao.findByPrimaryKey("code", entity.getCode());
//        RestrictionsContainer container = RestrictionsContainer.newInstance();
//        List<JournalComptable> journaux = journaldao.filter(container.getPredicats(), null, null, 0 , -1);
//        String[] shortMonths =  {"janv","févr","mars","avr","mai","juin","juil","août","sept","oct","nov","déc"};
//        if(journaux!=null){
//            for(JournalComptable journal : journaux){
//                Date begin = entity.getDebut();
//                while(DateHelper.convertToString(begin, "yyyy-MM-dd")
//                        .compareTo(DateHelper.convertToString(entity.getFin(), "yyyy-MM-dd"))<=0){
//                    String code = shortMonths[begin.getMonth()]+"."+DateHelper.convertToString(begin, "yy");
//                    JournalSaisie saisie = new JournalSaisie(code, entity, DateHelper.getFirstDayOfMonth(begin)
//                            , DateHelper.getLastDayOfMonth(begin));
//                    saisie.setJournal(journal);
//                    journalsaisiedao.save(saisie);
//                    begin = DateHelper.nextMonth(begin);
//                }//end while(DateHelper.convertToString(begin, "yyyy-MM-dd")
//            }//end for(JournalComptable journal : journaux)
//        }//end if(journaux!=null)o
//        if(entity.isOuvert()){
//            List<ExerciceComptable> datas = dao.filter(RestrictionsContainer.newInstance().getPredicats(), null, null, 0, -1);
//            if(datas!=null){
//                for(ExerciceComptable ex:datas){
//                    if(ex.isOuvert()){
//                        ex.setOuvert(false);
//                        dao.update(ex.getId(), ex);
//                    }
//                }
//            }//end if(datas!=null){
//        }//end if(entity.isOuvert()){
    }

    @Override
    public void processBeforeUpdate(ExerciceComptable entity) {
//        if(entity.isOuvert()){
//            List<ExerciceComptable> datas = dao.filter(RestrictionsContainer.newInstance().getPredicats(), null, null, 0, -1);
//            if(datas!=null){
//                for(ExerciceComptable ex:datas){
//                    if(ex.isOuvert()){
//                        ex.setOuvert(false);
//                        dao.update(ex.getId(), ex);
//                    }
//                }
//            }//end if(datas!=null){
//        }//end if(entity.isOuvert()){
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ExerciceComptable> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<ExerciceComptable> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<ExerciceComptable> result = new ArrayList<ExerciceComptable>();
        for(ExerciceComptable data:datas){
            result.add(new ExerciceComptable(data));
        }
        return result;
    }

    @Override
    public ExerciceComptable find(String propertyName, Long entityID) {
        ExerciceComptable data =  super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        ExerciceComptable result = new ExerciceComptable(data);
        for(PeriodeComptable periode:data.getPeriodes()){
            result.getPeriodes().add(new PeriodeComptable(periode));
        }
        return result;
    }

    @Override
    public ExerciceComptable delete(Long id) {
        ExerciceComptable data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new ExerciceComptable(data);
    }
    
    
    @Override
    public ExerciceComptable mensuelle(ExerciceComptable entity) {
       Date now = new Date();            
        for(int i=0;i<12;i++){
            Date curent = DateHelper.nextMonth(entity.getDebut(), i);
            SimpleDateFormat formater = new SimpleDateFormat("MM/yy");
            PeriodeComptable periode = new PeriodeComptable(formater.format(curent), DateHelper.getFirstDayOfMonth(curent), DateHelper.getLastDayOfMonth(curent), -1, null, null, (now.getTime()+1L));
            periode.setCode(formater.format(curent));
            periode.setExercice(entity);
            periode.setOuvert(Boolean.FALSE);
            periode.setState("close");
            periodedao.save(periode);
        }//end for(int i=0;i<12;i++){
        //mise a jour de l'etat
        entity.setState("close");
        entity = dao.update(entity.getId(), entity);
        ExerciceComptable result = new ExerciceComptable(entity);
        for(PeriodeComptable periode:entity.getPeriodes()){
            result.getPeriodes().add(new PeriodeComptable(periode));
        }//end for(PeriodeComptable periode:entity.getPeriodes()){
        return result;
    }

    @Override
    public ExerciceComptable trimestrielle(ExerciceComptable entity) {
         //To change body of generated methods, choose Tools | Templates.
        //Trimestre 1
         Date now = new Date();            
        for(int i=0;i<12;i=i+3){
            Date curent = DateHelper.nextMonth(entity.getDebut(), i);
            SimpleDateFormat formater = new SimpleDateFormat("MM/yy");
            PeriodeComptable periode = new PeriodeComptable(formater.format(curent), DateHelper.getFirstDayOfMonth(curent), DateHelper.getLastDayOfMonth(curent), -1, null, null, (now.getTime()+1L));
            periode.setCode(formater.format(curent));
            periode.setExercice(entity);
            periode.setOuvert(Boolean.FALSE);
            periode.setState("close");
            periodedao.save(periode);
        }//end for(int i=0;i<12;i++){
        //mise a jour de l'etat
        entity.setState("close");
        entity = dao.update(entity.getId(), entity);
        ExerciceComptable result = new ExerciceComptable(entity);
        for(PeriodeComptable periode:entity.getPeriodes()){
            result.getPeriodes().add(new PeriodeComptable(periode));
        }//end for(PeriodeComptable periode:entity.getPeriodes()){
        return result;
    }

    @Override
    public ExerciceComptable open(ExerciceComptable entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExerciceComptable close(ExerciceComptable entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
