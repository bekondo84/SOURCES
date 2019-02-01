/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Observer;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_PGRM_SMS")
public class ProgramSMS extends BaseElement implements Serializable,Comparable<ProgramSMS>{

    @Predicate(label = "phones.numbers",optional = false,search = true)
    private String phone;
    
    @ManyToOne
    @JoinColumn(name = "MOD_ID")
    @Predicate(label = "modele",type = SMSModel.class,target = "many-to-one",observable = true)
    private SMSModel model;   
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Predicate(label = "date.to.send",type = Date.class,target = "date",search = true,optional = false)
    private Date date ;
    
    @Predicate(label = "time.to.send",target = "time",search = true)
    private String hours ;
    
    @Lob
    @Predicate(label = "message",target = "textarea",search = true,optional = false,group = true,groupName = "group1",groupLabel = "message")
    @Observer(observable = "model",source = "field:text")
    private String text ;
    
     @ManyToOne
    @Predicate(label = "groupe.contact",type = GroupeContact.class,search = true,target = "many-to-one",group = true,groupName = "group2",groupLabel = "other.destiner",observable = true)
    private GroupeContact groupe;
     
    @ManyToMany
    @Predicate(label = "contacts",type = Contact.class,target = "many-to-many-list",group = true,groupName = "group2",groupLabel = "other.destiner")
    @Observer(observable = "groupe",source = "method:contacts",parameters = "groupe")
   private List<Contact> contacts = new ArrayList<Contact>();

    public ProgramSMS() {
    }

    /**
     * 
     * @param phone
     * @param model
     * @param date
     * @param hours
     * @param text
     * @param groupe
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public ProgramSMS(String phone, SMSModel model, Date date, String hours, String text, GroupeContact groupe, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.phone = phone;
        this.model = model;
        this.date = date;
        this.hours = hours;
        this.text = text;
        this.groupe = groupe;
    }

    public ProgramSMS(ProgramSMS entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.phone = entity.phone;
        this.model = entity.model;
        this.date = entity.date;
        this.hours = entity.hours;
        this.text = entity.text;
        if(entity.groupe!=null){
            this.groupe = new GroupeContact(entity.groupe);
        }
    }
    

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SMSModel getModel() {
        return model;
    }

    public void setModel(SMSModel model) {
        this.model = model;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GroupeContact getGroupe() {
        return groupe;
    }

    public void setGroupe(GroupeContact groupe) {
        this.groupe = groupe;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
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
    public String getModuleName() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "send.sms"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(ProgramSMS o) {
        return phone.compareTo(o.phone); //To change body of generated methods, choose Tools | Templates.
    }
    
}
