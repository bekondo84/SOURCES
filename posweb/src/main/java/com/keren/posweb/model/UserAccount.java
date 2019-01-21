/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.posweb.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_USER")
public class UserAccount extends BaseElement implements Serializable,Comparable<UserAccount>{

     @Predicate(label = "LOGIN" ,nullable = false ,optional = false,min = 4,unique = true,updatable = false,search = true,sequence = 3)
    private String intitule ;     
    
    @Predicate(label = "ADRESSE ELECTRONIQUE",target = "email",unique = false,optional = false,search = true,sequence = 4)
    private String courriel ;
    
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

    @Override
    public String getOwnermodule() {
        return "kerencore"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "kerencore"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "user.account"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "users.accounts"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
