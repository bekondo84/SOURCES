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
import java.util.List;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author BEKO
 */
public class SendSMS extends BaseElement implements Serializable,Comparable<SendSMS>{

    @Predicate(label = "phones.numbers",search = true)
    private String phone;
    
    @ManyToOne
    @Predicate(label = "modele",type = SMSModel.class,target = "many-to-one",observable = true)
    private SMSModel model;   
    
     
    @Lob
    @Predicate(label = " ",target = "textarea",search = true,optional = false,group = true,groupName = "group1",groupLabel = "message")
    @Observer(observable = "model",source = "field:text")
    private String text ;
    
     @ManyToOne
    @Predicate(label = "groupe.contact",type = GroupeContact.class,target = "many-to-one",group = true,groupName = "group2",groupLabel = "other.destiner",observable = true)
    private GroupeContact groupe;
     
    @ManyToMany
    @Predicate(label = "contacts",type = Contact.class,target = "many-to-many-list",group = true,groupName = "group2",groupLabel = "other.destiner")
    @Observer(observable = "groupe",source = "method:contacts",parameters = "groupe")
   private List<Contact> contacts = new ArrayList<Contact>();

    public SendSMS() {
    }

    public SendSMS(String phone, SMSModel model, String text) {
        this.phone = phone;
        this.model = model;
        this.text = text;
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
    public int compareTo(SendSMS o) {
        return phone.compareTo(o.phone); //To change body of generated methods, choose Tools | Templates.
    }
    
}
