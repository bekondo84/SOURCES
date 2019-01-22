/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.core.base.State;
import com.megatim.common.annotations.KHeader;
import com.megatim.common.annotations.KHeaders;
import com.megatim.common.annotations.KValue;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_POS")
@KHeaders(value={
   @KHeader(type = "button",name = "work1",label = "enable",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"desable"},pattern = "btn btn-success"
       , value = @KValue("{'model':'posweb','entity':'pointvente','method':'enable'}")
   ),@KHeader(type = "button",name = "work1",label = "desable",target = "workflow",roles = {"administrateur","gestionnaire"},states = {"enable"},pattern = "btn btn-danger"
       , value = @KValue("{'model':'posweb','entity':'pointvente','method':'desable'}")
   )
},statubar = true)
public class PointVente extends BaseElement implements Serializable,Comparable<PointVente>{

    @Predicate(label = "Reference ",optional = false,unique = true,search = true)
    private String code;
    
    @Predicate(label = "Nom du POS ",optional = false,search = true)
    private String intitule ;
    
    @ManyToOne
    @JoinColumn(name = "SOC_ID")
    @Predicate(label = "Socièté",type = Structure.class,target = "many-to-one",optional = true,search = true)
    private Structure societe ;
    
    @Predicate(label = "Actif",type = Boolean.class)
    private Boolean actif =Boolean.TRUE;
    
    @Predicate(label = "cashiers",type = Caissier.class,target = "many-to-many-list",search = true,edittable = true,group = true,groupName = "group1",groupLabel = "cashiers")
    @ManyToMany
    @JoinTable(name = "T_POS_CASHIER_POS"
            ,joinColumns = @JoinColumn(name = "POS_ID")
            ,inverseJoinColumns = @JoinColumn(name = "CASHIER_ID"))
    private List<Caissier> cashiers = new ArrayList<Caissier>();
    
    @Predicate(label = " ",hide = true)
    private String state ="enable";

    public PointVente() {
        
    }

    /**
     * 
     * @param entity    
     */
    public PointVente(PointVente entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        this.intitule = entity.intitule;
        if(entity.societe!=null){
            this.societe = entity.societe;
        }
    }
    
    

    public PointVente(String code, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    @Override
    public String getDesignation() {
        return intitule; //To change body of generated methods, choose Tools | Templates.
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Structure getSociete() {
        return societe;
    }

    public void setSociete(Structure societe) {
        this.societe = societe;
    }

    public List<Caissier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(List<Caissier> cashiers) {
        this.cashiers = cashiers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    

    @Override
    public String getModuleName() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "points.of.sales"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "point.of.sale"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        setSearchkeys(code);
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return super.isDesableupdate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivatefollower() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> getStates() {
        states = new ArrayList<State>();
        states.add(new State("enable", "enable"));
        states.add(new State("desable", "desable"));
        return states; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActivefilelien() {
        return true; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSerial() {
        return "210120191436pos"; //To change body of generated methods, choose Tools | Templates.
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
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(PointVente o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
