
package com.basaccount.core.impl.ventes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.ventes.NoteFraisVenteManagerLocal;
import com.basaccount.core.ifaces.ventes.NoteFraisVenteManagerRemote;
import com.basaccount.dao.ifaces.ventes.NoteFraisVenteDAOLocal;
import com.basaccount.model.achats.LigneNoteFrais;
import com.basaccount.model.achats.NoteFrais;
import com.basaccount.model.comptabilite.Taxe;
import com.basaccount.model.ventes.LigneNoteFraisVente;
import com.basaccount.model.ventes.NoteFraisVente;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "NoteFraisVenteManager")
public class NoteFraisVenteManagerImpl
    extends AbstractGenericManager<NoteFraisVente, Long>
    implements NoteFraisVenteManagerLocal, NoteFraisVenteManagerRemote
{

    @EJB(name = "NoteFraisVenteDAO")
    protected NoteFraisVenteDAOLocal dao;

    public NoteFraisVenteManagerImpl() {
    }

    @Override
    public GenericDAO<NoteFraisVente, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<NoteFraisVente> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<NoteFraisVente> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<NoteFraisVente> result = new ArrayList<NoteFraisVente>();
        for(NoteFraisVente data:datas){
            result.add(new NoteFraisVente(data));
        }
        return result;
    }

    @Override
    public NoteFraisVente find(String propertyName, Long entityID) {
        NoteFraisVente data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        NoteFraisVente result = new NoteFraisVente(data);
        for(LigneNoteFrais ligne:data.getNotes()){
            LigneNoteFrais ligne2 = new LigneNoteFrais(ligne);
            for(Taxe tax:ligne.getTaxes()){
                ligne2.getTaxes().add(new Taxe(tax));
            }//endfor(Taxe tax:ligne.getTaxes()){
            result.getNotes().add(ligne2);
        }//end for(LigneNoteFrais ligne:data.getNotes()){
        return result;
    }

    @Override
    public NoteFraisVente delete(Long id) {
        NoteFraisVente data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new NoteFraisVente(data);
    }
    
     @Override
    public void processBeforeUpdate(NoteFraisVente entity) {
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
    public void processBeforeSave(NoteFraisVente entity) {
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
