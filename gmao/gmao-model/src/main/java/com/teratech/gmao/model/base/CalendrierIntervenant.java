/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.gmao.model.base;

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
@Table(name = "T_CAINGM")
public class CalendrierIntervenant extends BaseElement implements Serializable,Comparable<CalendrierIntervenant>{

    @ManyToOne
    @JoinColumn(name = "INT_ID")
    @Predicate(label = "Intervenant",type = Intervenant.class,target = "many-to-one",optional = false,search = true)
    private Intervenant intervenant ;
    
    @ManyToOne
    @JoinColumn(name = "CAU_ID")
    @Predicate(label = "Cause",type = CauseException.class,target = "many-to-one",optional = false,search = true)
    private CauseException cause ;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "Date",type = Date.class,target = "date",optional = false,search = true)
    private Date day ;
    
    @Predicate(label = "Heure de fin",target = "time",optional = false,search = true)
    private String hfin ;
    
    @Predicate(label = "Heure debut",target = "time",optional = false,search = true)
    private String hdebut ;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date debut ;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fin ;

    public CalendrierIntervenant() {
    }

    public CalendrierIntervenant(Intervenant intervenant, CauseException cause, Date day, String hdebut, String hfin) {
        this.intervenant = intervenant;
        this.cause = cause;
        this.day = day;
        this.hdebut = hdebut;
        this.hfin = hfin;
    }

    public CalendrierIntervenant(Intervenant intervenant, CauseException cause, Date day, String hdebut, String hfin, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.intervenant = intervenant;
        this.cause = cause;
        this.day = day;
        this.hdebut = hdebut;
        this.hfin = hfin;
    }
    
    public CalendrierIntervenant(CalendrierIntervenant entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        if(entity.intervenant!=null){
            this.intervenant = new Intervenant(entity.intervenant);
        }
        this.cause = entity.cause;
        this.day = entity.day;
        this.hdebut = entity.hdebut;
        this.hfin = entity.hfin;
        this.debut = entity.debut;
        this.fin = entity.fin;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public CauseException getCause() {
        return cause;
    }

    public void setCause(CauseException cause) {
        this.cause = cause;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getHdebut() {
        return hdebut;
    }

    public void setHdebut(String hdebut) {
        this.hdebut = hdebut;
    }

    public String getHfin() {
        return hfin;
    }

    public void setHfin(String hfin) {
        this.hfin = hfin;
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
    
    

    @Override
    public String getDesignation() {
        return intervenant.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechgmao"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Calendrier Intervenant"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Calendrier Intervenant"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(CalendrierIntervenant o) {
        //To change body of generated methods, choose Tools | Templates.
        return day.compareTo(o.day);
    }
    
}
