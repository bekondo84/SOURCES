/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.achat.model.comptabilite;

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
    @Predicate(label = "echeance",target = "date",type = Date.class,optional = false,search = true)
    private Date date ;
    
    @Predicate(label = "montant",type = Double.class,optional = false,search = true)
    private Double montant = 0.0;
    
   @ManyToOne
    @JoinColumn(name = "MORE_ID")
    @Predicate(label = "mode.reglement",type = ModeReglement.class,target = "many-to-one",optional = false,search = true)
    private ModeReglement mode ;

    @Predicate(label = "etat",type = Boolean.class,updatable = false,editable = true,search = true)
    private Boolean etat =Boolean.FALSE;
    
    
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
     * @param date
     * @param mode
     * @param id
     * @param designation
     * @param moduleName 
     */
    public EcheanceReglement(Date date, ModeReglement mode, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.date = date;
        this.mode = mode;
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

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "echeances.de.reglement"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
         return "echeance.de.reglement"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesablecreate() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(EcheanceReglement o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
