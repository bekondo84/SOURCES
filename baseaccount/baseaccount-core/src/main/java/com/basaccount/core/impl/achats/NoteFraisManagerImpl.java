
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.NoteFraisManagerLocal;
import com.basaccount.core.ifaces.achats.NoteFraisManagerRemote;
import com.basaccount.dao.ifaces.achats.NoteFraisDAOLocal;
import com.basaccount.model.achats.LigneNoteFrais;
import com.basaccount.model.achats.NoteFrais;
import com.basaccount.model.comptabilite.Taxe;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "NoteFraisManager")
public class NoteFraisManagerImpl
    extends AbstractGenericManager<NoteFrais, Long>
    implements NoteFraisManagerLocal, NoteFraisManagerRemote
{

    @EJB(name = "NoteFraisDAO")
    protected NoteFraisDAOLocal dao;

    public NoteFraisManagerImpl() {
    }

    @Override
    public GenericDAO<NoteFrais, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<NoteFrais> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<NoteFrais> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<NoteFrais> result = new ArrayList<NoteFrais>();
        for(NoteFrais data:datas){
            result.add(new NoteFrais(data));
        }
        return result;
    }

    @Override
    public NoteFrais find(String propertyName, Long entityID) {
        NoteFrais data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        NoteFrais entity = new NoteFrais(data);     
        for(LigneNoteFrais ligne:data.getNotes()){
            LigneNoteFrais ligne2 = new LigneNoteFrais(ligne);
            for(Taxe tax:ligne.getTaxes()){
                ligne2.getTaxes().add(new Taxe(tax));
            }//endfor(Taxe tax:ligne.getTaxes()){
            entity.getNotes().add(ligne2);
        }
        return entity;
    }

    @Override
    public NoteFrais delete(Long id) {
        NoteFrais entity = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new NoteFrais(entity);
    }

    @Override
    public void processBeforeUpdate(NoteFrais entity) {
         Double totalht = 0.0;
        Double taxes = 0.0;
        Double totalttc = 0.0;
        for(LigneNoteFrais ligne:entity.getNotes()){
            double ltaxe = 0.0;
            for(Taxe taxe : ligne.getTaxes()){
                ltaxe+=(taxe.getCalculTaxe().equalsIgnoreCase("0")? taxe.getMontant():(ligne.getMontant()*taxe.getMontant())/100);
            }//end for(Taxe taxe : ligne.getTaxes()){
            ligne.setTaxe(ltaxe);
            ligne.setTotal(ligne.getMontant()+ligne.getTaxe());
            totalht+= ligne.getMontant();
            taxes += ligne.getTaxe();
            totalttc+=ligne.getTotal();
            if(ligne.getId()<=0){
                ligne.setId(-1L);
            }//end if(ligne.getId()<=0){
        }//end for(LigneNoteFrais ligne:entity.getNotes()){
        entity.setTotalht(totalht);
        entity.setTotaltaxes(taxes);
        entity.setTotalttc(totalttc);
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(NoteFrais entity) {
        Double totalht = 0.0;
        Double taxes = 0.0;
        Double totalttc = 0.0;
        for(LigneNoteFrais ligne:entity.getNotes()){
            double ltaxe = 0.0;
            for(Taxe taxe : ligne.getTaxes()){
                ltaxe+=(taxe.getCalculTaxe().equalsIgnoreCase("0")? taxe.getMontant():(ligne.getMontant()*taxe.getMontant())/100);
            }//end for(Taxe taxe : ligne.getTaxes()){
            ligne.setTaxe(ltaxe);
            ligne.setTotal(ligne.getMontant()+ligne.getTaxe());
            totalht+= ligne.getMontant();
            taxes += ligne.getTaxe();
            totalttc+=ligne.getTotal();
            if(ligne.getId()<=0){
                ligne.setId(-1L);
            }//end if(ligne.getId()<=0){
        }//end for(LigneNoteFrais ligne:entity.getNotes()){
        entity.setTotalht(totalht);
        entity.setTotaltaxes(taxes);
        entity.setTotalttc(totalttc);
        entity.setState("etabli");
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
