/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.tiers.Tier;
import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_ECRE")
public class EcheanceReglement extends BaseElement implements Serializable,Comparable<EcheanceReglement>{

    @Temporal(TemporalType.DATE)
    @Predicate(label = "echeance",type = Date.class,target = "date",optional = false,search = true)
    private Date date ;
    
    @Predicate(label = "montant",type = Double.class,optional = false,search = true)
    private Double montant = 0.0;
    
   @ManyToOne
    @JoinColumn(name = "MORE_ID")
    @Predicate(label = "mode.reglement",type = ModeReglement.class,target = "many-to-one",optional = false,search = true)
    private ModeReglement mode ;
   
   @Predicate(label = "montant.percu",type = Double.class,optional = false,search = true)
    private Double percu = 0.0;
    
   @Predicate(label = "montant.solde",type = Double.class,optional = false,search = true,compute = true,values = "this.montant;-;this.percu")
    private Double solde = 0.0;
    
    @Predicate(label = "etat",type = Boolean.class,updatable = false,editable = false,search = true)
    private Boolean etat =Boolean.FALSE;
    
    @ManyToOne
    @JoinColumn(name = "TIER_ID")
    private Tier tier ;

    /**
     * 
     * @param date
     * @param mode 
     */
    public EcheanceReglement(Date date, ModeReglement mode) {
        this.date = date;
        this.mode = mode;
    }

    /**
     * 
     * @param entity 
     */
    public EcheanceReglement(EcheanceReglement entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.date = entity.date;
        this.montant = entity.montant;
        if(entity.mode!=null){
            this.mode = new ModeReglement(entity.mode);
        }
        if(entity.tier!=null){
            this.tier = new Tier(entity.tier);
        }
        this.percu = entity.percu;
        this.solde = this.montant-(this.percu!=null ? this.percu:0.0);
        this.etat = entity.etat;
    }

  
    

    public EcheanceReglement() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public ModeReglement getMode() {
        return mode;
    }

    public void setMode(ModeReglement mode) {
        this.mode = mode;
    }

    public Double getPercu() {
        return percu;
    }

    public void setPercu(Double percu) {
        this.percu = percu;
    }

    public Double getSolde() {
        solde = this.montant - (percu==null ? 0.0:percu);
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }
    
    

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "echeances.reglements"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
         return "echeance.reglement"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(EcheanceReglement o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
