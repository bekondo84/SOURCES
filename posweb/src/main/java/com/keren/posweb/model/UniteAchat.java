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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_UNAC")
public class UniteAchat extends BaseElement implements Serializable,Comparable<UniteAchat>{

    @Predicate(label = "code",optional = false,unique = true,search = true)
    private String code ;    
    
    @Predicate(label = "actif",type = Boolean.class)
    private Boolean actif = Boolean.TRUE;
    
    @ManyToOne
    @JoinColumn(name = "UNGE_ID")
    @Predicate(label = "unite.de.gestion",type = UniteGestion.class,target = "many-to-one",search = true)
    private UniteGestion unite ;
    
    @Predicate(label = "precision",type = Double.class,search = true)
    private Double coeff = 0.0;

    public UniteAchat(String code, Boolean actif, UniteGestion unite) {
        this.code = code;
        this.actif = actif;
        this.unite = unite;
    }

    public UniteAchat(String code, Boolean actif, UniteGestion unite, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.code = code;
        this.actif = actif;
        this.unite = unite;
    }

    public UniteAchat() {
    }

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public UniteGestion getUnite() {
        return unite;
    }

    public void setUnite(UniteGestion unite) {
        this.unite = unite;
    }

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }
    

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "unites.achats"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "unite.achat"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(UniteAchat o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
