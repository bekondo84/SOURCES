/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_PAYS")
public class Pays extends BaseElement implements Serializable,Comparable<Pays>{
    
     @Predicate(label = "image",target = "image",search = false)
     private String image ;
     
    @Predicate(label = "intitule" ,optional = false,unique = true,search = true)
    private String intitule ;
    
    @Predicate(label = "code" ,optional = false,updatable = false,unique = true,search = true)
    private String code;
    
    @ManyToOne
    @JoinColumn(name = "DEVISE_ID")
    @Predicate(label = "devise",type = Devise.class,target="many-to-one",search = true)
    private Devise devise ;
    
    @Predicate(label = "liste.regions" , type = com.core.referentiels.Region.class,target = "one-to-many" ,group = true,groupName = "regions",groupLabel = "Régions", edittable = true)
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYS_ID")
    private List<Region> etats = new ArrayList<Region>();

    public Pays() {
    }

    /**
     * 
     * @param image
     * @param intitule
     * @param code
     * @param devise
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public Pays(String image, String intitule, String code, Devise devise, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.image = image;
        this.intitule = intitule;
        this.code = code;
        this.devise = devise;
    }
    
    /**
     * 
     * @param entity 
     */
    public Pays(Pays entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.image = entity.image;
        this.intitule = entity.intitule;
        this.code = entity.code;
        this.devise = entity.devise;
    }
    
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesignation() {
        return intitule; //To change body of generated methods, choose Tools | Templates.
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public List<Region> getEtats() {
        return etats;
    }

    public void setEtats(List<Region> etats) {
        this.etats = etats;
    }
    
    

    @Override
    public String getModuleName() {
        return "teratechstock"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "pays"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "pays"; //To change body of generated methods, choose Tools | Templates.
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
    public int compareTo(Pays o) {
        return code.compareTo(o.code); //To change body of generated methods, choose Tools | Templates.
    }
}
