/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.stock.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Commercial_2
 */
@Entity
@Table(name = "T_REGION")
@XmlRootElement
public class Region extends BaseElement implements Serializable,Comparable<Region>{

    @Predicate(label = "code" ,optional = false,unique = true,search = true)
     private String code ;
    
    @Predicate(label = "intitule" ,optional = false,unique = true,search = true)
     private String intitule ;

    /**
     * 
     */
    public Region() {
    }

    /**
     * 
     * @param code
     * @param designation 
     */
    public Region(String code, String designation) {
        this.code = code;
        this.intitule = designation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesignation() {
        return intitule;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
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
    public String getListTitle() {
        return "etats";//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "etat"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public String getModuleName() {
         //To change body of generated methods, choose Tools | Templates.
        return "teratechstock";
    }
    
    
    
    @Override
    public int compareTo(Region o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return "Region{" + "code=" + code + ", designation=" + designation + '}';
    }
    
    
}
