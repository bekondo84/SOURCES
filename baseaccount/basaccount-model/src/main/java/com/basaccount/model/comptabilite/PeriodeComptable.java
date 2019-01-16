/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.comptabilite;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_PECO_COM")
public class PeriodeComptable extends BaseElement implements Serializable,Comparable<PeriodeComptable>{

    @Predicate(label = "reference",search = true,optional = false,unique = true,editable = false)
    private String code ;    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "date.debut",type = Date.class,target = "date",search = true,updatable = false)
    private Date debut;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "date.fin",type = Date.class,target = "date",search = true,updatable = false)
    private Date fin ;
    
    @Predicate(label = "periode.statut",type = Boolean.class,search = true)
    private Boolean ouvert = Boolean.FALSE;
    
    @Predicate(label = "state",hide = true)
    private String state = "close";
    
    @ManyToOne
    @JoinColumn(name = "EXER_ID")
    private ExerciceComptable exercice ;

    public PeriodeComptable() {
    }

    /**
     * 
     * @param code
     * @param debut
     * @param fin
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public PeriodeComptable(String code, Date debut, Date fin, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.debut = debut;
        this.fin = fin;
    }
   
    /**
     * 
     * @param entity 
     */
    public PeriodeComptable(PeriodeComptable entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        this.debut = entity.debut;
        this.fin = entity.fin;
        this.state = entity.state;
        this.exercice = entity.exercice ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Boolean getOuvert() {
        return ouvert;
    }

    public void setOuvert(Boolean ouvert) {
        this.ouvert = ouvert;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ExerciceComptable getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceComptable exercice) {
        this.exercice = exercice;
    }
    
    

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
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
    public boolean isActivefilelien() {
        return super.isActivefilelien(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "160120191231PECO"; //To change body of generated methods, choose Tools | Templates.
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
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "periode.comptable.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "periode.comptable.detail"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(PeriodeComptable o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
    
}
