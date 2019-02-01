/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "T_GRPE_SMS")
public class GroupeContact extends BaseElement implements Serializable,Comparable<GroupeContact>{

    @Predicate(label = "reference",search = true,optional = false,unique = true)
    private String code ;
    
    @Predicate(label = "intitule",search = true)
    private String intitule;
    
    @ManyToOne
    @JoinColumn(name = "GRPE_ID")
    private GroupeContact parent ;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_GRPE_CNTS_SMS",joinColumns = @JoinColumn(name = "GRPE_ID"),inverseJoinColumns = @JoinColumn(name = "CNTS_ID"))
    @Predicate(label = " ",type = Contact.class,target = "many-to-many-list",group = true,groupName = "group1",groupLabel = "contacts")
    private List<Contact> contacts = new ArrayList<Contact>();

    public GroupeContact() {
    }

    public GroupeContact(String code, String intitule, GroupeContact parent, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.code = code;
        this.intitule = intitule;
        this.parent = parent;
    }

    
     public GroupeContact(GroupeContact entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.code = entity.code;
        this.intitule = entity.intitule;
        if(entity.parent!=null){
            this.parent = new GroupeContact(entity.parent);
        }
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

    public GroupeContact getParent() {
        return parent;
    }

    public void setParent(GroupeContact parent) {
        this.parent = parent;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String getOwnerentity() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return code; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "groupes.contacts"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "groupe.contact"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getSearchkeys() {
        return super.getSearchkeys(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(GroupeContact o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
