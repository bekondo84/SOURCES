/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.operations;

import com.core.base.State;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import com.teratech.stock.model.base.LienEmplacement;
import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author BEKO
 */
@Entity
@DiscriminatorValue("OUT")
public class LigneSortie extends LigneDocumentStock implements Serializable{

    @ManyToOne
    @JoinColumn(name = "LIEMP_ID")
    @Predicate(label = "emplacement.cible",type = LienEmplacement.class,target = "many-to-one",search = true,optional = false,observable = true)
    @Filter(value = "[{\"fieldName\":\"article\",\"value\":\"object.article\",\"searchfield\":\"code\",\"optional\":false,\"message\":\"Veuillez selectionner l'article\"},{\"fieldName\":\"entrpot\",\"value\":\"this.entrepot\",\"searchfield\":\"code\",\"optional\":false,\"message\":\"Veuillez selectionner le magasin\"}]")
    private LienEmplacement emplacement ;        
    
    @Predicate(label = "numero.serie.ou.lot",type = Lot.class,target = "many-to-one",optional = true,search = true)
    @ManyToOne
    @JoinColumn(name = "LOT_ID")
    @Filter(value = "[{\"fieldName\":\"lien\",\"value\":\"object.emplacement\",\"searchfield\":\"id\",\"optional\":false,\"message\":\"Veuillez sélectionner l'emplacement\"}]")
    private Lot lot ;
    
//    @Predicate(label = "Qté Disponible",type = Double.class,search = true,editable = false)
//    @Observer(observable = "emplacement",source = "field:stock")
    private Double qtedispo ;
    
    
    public LigneSortie() {
    }

    public LigneSortie(LigneSortie entity) {
        super(entity);
        if(entity.emplacement!=null){
            this.emplacement = new LienEmplacement(entity.emplacement);
        }
        if(entity.lot!=null){
            this.lot = new Lot(entity.lot);
        }//end if(lot!=null){
        this.qtedispo = entity.qtedispo;
    }

    public LienEmplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(LienEmplacement emplacement) {
        this.emplacement = emplacement;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Double getQtedispo() {
        return qtedispo;
    }

    public void setQtedispo(Double qtedispo) {
        this.qtedispo = qtedispo;
    }

    

    @Override
    public String getOwnermodule() {
        return "teratechstock"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return super.isDesableupdate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivatefollower() {
        return super.isActivatefollower(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        return super.getStates(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivefilelien() {
        return super.isActivefilelien(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return super.isDesabledelete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return super.isDesablecreate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return super.isCreateonfield(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "lignes.sortie"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
