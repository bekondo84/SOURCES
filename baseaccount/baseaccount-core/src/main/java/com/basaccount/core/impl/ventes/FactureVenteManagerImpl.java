
package com.basaccount.core.impl.ventes;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.ventes.FactureVenteManagerLocal;
import com.basaccount.core.ifaces.ventes.FactureVenteManagerRemote;
import com.basaccount.dao.ifaces.ventes.FactureVenteDAOLocal;
import com.basaccount.model.achats.Acompte;
import com.basaccount.model.achats.DocumentAchatState;
import com.basaccount.model.achats.EcheanceReglement;
import com.basaccount.model.comptabilite.Taxe;
import com.basaccount.model.ventes.FactureVente;
import com.basaccount.model.ventes.LigneFactureVente;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "FactureVenteManager")
public class FactureVenteManagerImpl
    extends AbstractGenericManager<FactureVente, Long>
    implements FactureVenteManagerLocal, FactureVenteManagerRemote
{

    
    @EJB(name = "FactureVenteDAO")
    protected FactureVenteDAOLocal dao;

    public FactureVenteManagerImpl() {
    }

    @Override
    public GenericDAO<FactureVente, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public Long count(List<Predicat> predicats) {
        return super.count(predicats); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FactureVente> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("typedocument", DocumentAchatState.COMPTABILITE);
        predicats.addAll(container.getPredicats());
        List<FactureVente> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<FactureVente> result = new ArrayList<FactureVente>();
        for(FactureVente data:datas){
            result.add(new FactureVente(data));
        }
        return result;
    }

    @Override
    public List<FactureVente> findAll() {
        List<FactureVente> datas = super.findAll();//To change body of generated methods, choose Tools | Templates.
        List<FactureVente> result = new ArrayList<FactureVente>();
        for(FactureVente data:datas){
            result.add(new FactureVente(data));
        }
        return result;
    }

    @Override
    public FactureVente find(String propertyName, Long entityID) {
        FactureVente data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        FactureVente result = new FactureVente(data);
        result.setPiececomptable(data.getPiececomptable());        
        for(LigneFactureVente ligne:data.getLignes()){
            result.getLignes().add(new LigneFactureVente(ligne));
        }
        for(Acompte acom:data.getAcomptes()){
            result.getAcomptes().add(new Acompte(acom));
        }
        for(EcheanceReglement ech:data.getEcheances()){
            result.getEcheances().add(new EcheanceReglement(ech));
        }
       return result;
    }

    @Override
    public FactureVente delete(Long id) {
        FactureVente data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new FactureVente(data);
    }

    @Override
    public void processBeforeUpdate(FactureVente entity) {
       computeFacture(entity);
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(FactureVente entity) {
        computeFacture(entity);
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param entity 
     */
    private void computeFacture(FactureVente entity){
        double totalht = 0.0;
        double totalttc = 0.0;
        double taxes = 0.0;
        double acomptes = 0.0;
        for(LigneFactureVente ligne:entity.getLignes()){
            if(ligne.getId()<=0){
                ligne.setId(-1L);
            }//end if(ligne.getId()<=0){
            double montant = ligne.getQuantite()*ligne.getPuht()*(100-(ligne.getRemise()!=null? ligne.getRemise():0.0))/100;
            totalht+=montant;
            for(Taxe tax : ligne.getTaxes()){
                double value = tax.getMontant();
                if(tax.getCalculTaxe().trim().equalsIgnoreCase("1")){
                    value = montant*tax.getMontant()/100;
                }//end if(tax.getCalculTaxe().trim().equalsIgnoreCase("1")){
                taxes+=value;
            }//end for(Taxe tax : ligne.getTaxes()){
        }//end  for(LigneFactureVente ligne:entity.getLignes()){
        for(Acompte acom:entity.getAcomptes()){
            if(acom.getId()<=0){
                acom.setId(-1L);
            }//end if(acom.getId()<=0){
            acomptes += acom.getMontant();
        }//end for(Acompte acom:entity.getAcomptes()){
        for(EcheanceReglement ech:entity.getEcheances()){
            if(ech.getId()<=0){
                ech.setId(-1L);
            }//end  if(ech.getId()<=0){
        }//end for(EcheanceReglement ech:entity.getEcheances()){
        totalttc = totalht + taxes;
        entity.setTotalht(totalht);
        entity.setTotalttc(totalttc);
        entity.setTaxes(taxes);
        entity.setTotalacompte(acomptes);
        entity.setEscompte(0.0);
        if(entity.getEscompte()!=null){
            entity.setTotalescompte(totalht*entity.getEscompte()/100);
        }//end if(entity.getEscompte()!=null){
    }//end  private void computeFacture(FactureVente entity){

}
