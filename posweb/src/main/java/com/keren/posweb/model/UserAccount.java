/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_USER")
public class UserAccount extends BaseElement implements Serializable,Comparable<UserAccount>{

     @Predicate(label = "image",target = "image",sequence = 1)
    private String image ="avatar.png";  
     
     @Predicate(label = "login" ,nullable = false ,optional = false,min = 4,unique = true,updatable = false,search = true,sequence = 3)
    private String intitule ;     
    
    @Predicate(label = "courriel",target = "email",unique = false,optional = false,search = true,sequence = 4)
    private String courriel ;
    
    
    @Predicate(label = "ACTIF",colsequence = 100,sequence = 2)
    private Boolean actif = Boolean.FALSE; 
    
     //@Predicate(label = "MOT DE PASSE",target = "password" ,optional = false,search = false)
    private String password ; 
    
    private String name = "admin";
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastconfirme = null;
    
    @Override
    public int compareTo(UserAccount o) {
         return courriel.compareTo(o.courriel); //To change body of generated methods, choose Tools | Templates.
    }

    public UserAccount() {
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
    
    

    @Override
    public String getOwnermodule() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "posweb"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "user.account"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "users.accounts"; //To change body of generated methods, choose Tools | Templates.
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
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastconfirme() {
        return lastconfirme;
    }

    public void setLastconfirme(Date lastconfirme) {
        this.lastconfirme = lastconfirme;
    }
    
    
    
}
