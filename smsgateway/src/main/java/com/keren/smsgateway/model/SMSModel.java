/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.File;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_MODEL_SMS")
public class SMSModel extends BaseElement implements Serializable,Comparable<SMSModel>{

    @Predicate(label = "reference",search = true,optional = false,unique = true)
    private String code ;
    
    @Predicate(label = "intitule",search = true)
    private String intitule;
    
    @Lob
    @Predicate(label = " ",optional = false,search = true,target = "textarea",group = true,groupName = "group1",groupLabel = "message")
    private String text ;

    public SMSModel() {
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param text 
     */
    public SMSModel(String code, String intitule, String text) {
        this.code = code;
        this.intitule = intitule;
        this.text = text;
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param text
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public SMSModel(String code, String intitule, String text, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.intitule = intitule;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getOwnerentity() {
        return "smsmodel"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        StringBuilder builder = new StringBuilder(code);
        builder.append(File.separator).append(intitule);
        return builder.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return super.getListTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return super.getEditTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(SMSModel o) {
         //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
