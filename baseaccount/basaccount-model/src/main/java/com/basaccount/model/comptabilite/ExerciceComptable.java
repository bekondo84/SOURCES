/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.comptabilite;

import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.KHeader;
import com.megatim.common.annotations.KHeaders;
import com.megatim.common.annotations.KValue;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_EXCO_COM")
@KHeaders(statubar = true,
          value= { @KHeader(type = "button",name = "work1",label = "button.mensual.period",target = "workflow",roles = {"administrateur","gestionnaire"}
                ,states = {"etabli"}, value = @KValue("{'model':'baseaccount','entity':'exercicecomptable','method':'mensuelle'}"),pattern = "btn btn-danger"
            ),
              @KHeader(type = "button",name = "work2",label = "button.trimester.period",target = "workflow",roles = {"administrateur","gestionnaire"}
                ,states = {"etabli"}, value = @KValue("{'model':'baseaccount','entity':'exercicecomptable','method':'trimestrielles'}"),pattern = "btn btn-danger"),
              @KHeader(type = "button",name = "work3",label = "button.open.exercice",target = "workflow",roles = {"administrateur","gestionnaire"}
                ,states = {"open","close"}, value = @KValue("{'model':'baseaccount','entity':'exercicecomptable','method':'open'}")),
              @KHeader(type = "button",name = "work4",label = "button.close.exercice",target = "workflow",roles = {"administrateur","gestionnaire"}
                ,states = {"open","close"}, value = @KValue("{'model':'baseaccount','entity':'exercicecomptable','method':'close'}"))
        })
public class ExerciceComptable extends BaseElement implements Serializable,Comparable<ExerciceComptable>{

    @Predicate(label = "code",optional = false,updatable = false,search = true)
    @Column(unique = true,nullable = false)
    private String code ;
    
    @Predicate(label = "libelle",search = true)
    private String libelle ;
    
//    @Predicate(label = "EXERCICE OUVERT",search = true,colsequence = 2,type = Boolean.class)
    private Boolean  ouvert = false ;
    
    @Predicate(label = "date.debut",type = Date.class,target = "date",optional = false,updatable = false,search = true)
    @Temporal(TemporalType.DATE)
    private Date debut ;
    
    @Predicate(label = "date.fin",type = Date.class,target = "date",optional = false,updatable = false,search = true)
    @Temporal(TemporalType.DATE)
    private Date fin ;
    
    private boolean active = false;
    
    @Predicate(label = "state",search = true,hide = true)
    private String state = "etabli";

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "exercice")
    @Predicate(label = " ",type = PeriodeComptable.class,target = "one-to-many",group = true,groupName = "group1",groupLabel = "periodes.comptable")
    private List<PeriodeComptable> periodes = new ArrayList<PeriodeComptable>();
    /**
     * 
     * @param code
     * @param debut
     * @param fin 
     */
    public ExerciceComptable(String code, Date debut, Date fin) {
        this.code = code;
        this.debut = debut;
        this.fin = fin;
    }

    /**
     * 
     * @param code
     * @param debut
     * @param fin
     * @param id
     * @param designation
     * @param moduleName 
     */
    public ExerciceComptable(String code, Date debut, Date fin, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.debut = debut;
        this.fin = fin;
    }
    
     public ExerciceComptable(ExerciceComptable entity) {
        super(entity.id, entity.designation, entity.moduleName,entity.compareid);
        this.code = entity.code;
        this.debut = entity.debut;
        this.fin = entity.fin;
        this.ouvert = entity.ouvert;
        this.state = entity.state;
        this.libelle = entity.libelle;
    }

    public ExerciceComptable() {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<PeriodeComptable> getPeriodes() {
        return periodes;
    }

    public void setPeriodes(List<PeriodeComptable> periodes) {
        this.periodes = periodes;
    }
    
    

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        List<State> statess = new ArrayList<State>();
        if(this.state.equalsIgnoreCase("etabli")){
            statess.add(new State("etabli", "brouillon"));
            statess.add(new State("open", "open"));
        }else if(this.state.equalsIgnoreCase("open")
                ||this.state.equalsIgnoreCase("close")){
            statess.add(new State("open", "open"));
            statess.add(new State("close", "close"));
        }
        return states; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public String getDesignation() {
        //To change body of generated methods, choose Tools | Templates.
        return code;
    }

    @Override
    public String getListTitle() {
        //To change body of generated methods, choose Tools | Templates.
        return "exercice.comptable.list";
    }

    @Override
    public String getEditTitle() {
        //To change body of generated methods, choose Tools | Templates.
        return "exercice.comptable.detail";
    }
    
     @Override
    public String getModuleName() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int compareTo(ExerciceComptable o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return "ExerciceComptable{" + "code=" + code + ", ouvert=" + ouvert + ", debut=" + debut + ", fin=" + fin + ", active=" + active + '}';
    }
    
}
