/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
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
    
    

    @Override
    public String getModuleName() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Points de Vente"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Point de vente"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        setSearchkeys(code);
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(PointVente o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
