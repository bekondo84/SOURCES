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
@KHeaders(statubar = true,
          value= {
              @KHeader(type = "button",name = "work3",label = "button.open.periode",target = "workflow",roles = {"administrateur","gestionnaire"}
                ,states = {"close"}, value = @KValue("{'model':'baseaccount','entity':'periodecomptable','method':'open','critical':true,'alert':'baseaccount.open.alert'}")),
              @KHeader(type = "button",name = "work4",label = "button.close.periode",target = "workflow",roles = {"administrateur","gestionnaire"}
                ,states = {"open"}, value = @KValue("{'model':'baseaccount','entity':'periodecomptable','method':'close','critical':true,'alert':'baseaccount.close.alert'}"))
        })
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
    @Predicate(label = "Exercice",type = ExerciceComptable.class,target = "many-to-one",hide = true)
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
        if(entity.exercice!=null){
            this.exercice = new ExerciceComptable(entity.exercice) ;
        }
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
        return true; //To change body of generated methods, choose Tools | Templates.
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
    public List<State> getStates() {
        List<State> statess = new ArrayList<State>();
        statess.add(new State("open", "open"));
        statess.add(new State("close", "close"));
        return statess; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(PeriodeComptable o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
    
}
