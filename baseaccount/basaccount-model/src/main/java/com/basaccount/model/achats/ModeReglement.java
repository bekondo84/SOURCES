/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basaccount.model.achats;

import com.basaccount.model.comptabilite.Compte;
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
@Table(name = "T_MORE")
public class ModeReglement extends BaseElement implements Serializable,Comparable<ModeReglement>{

    @Predicate(label = "code.reglement",optional = false ,search = true)
    private String code ;
    
    @Predicate(label = "intitule" ,search = true)
    private String label ;
    
    @Predicate(label = "type.reglement",target = "combobox",values = "Aucun;Espèces;Chèque;Carte bancaire" ,search = true)
    private String type ="0";
    
    @ManyToOne
    @JoinColumn(name = "CMPT_ID")
    @Predicate(label = "compte.associe",type = Compte.class,target = "many-to-one",search = true)
    private Compte compte ;

    public ModeReglement(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public ModeReglement(ModeReglement entity) {
        super(entity.getId(), entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.getCode();
        this.label = entity.getLabel();
        this.type = entity.getType();
        if(entity.getCompte()!=null){
            this.compte = new Compte(entity.getCompte());
        }
    }

    

    public ModeReglement() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getDesignation() {
        return code+" - "+label; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "Modes de reglements"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "Mode de règlement"; //To change body of generated methods, choose Tools | Templates.
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(ModeReglement o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
