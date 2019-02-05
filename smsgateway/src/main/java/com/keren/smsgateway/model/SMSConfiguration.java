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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *Configuration de la plate forme de SMS
 * @author BEKO
 */
@Entity
@Table(name = "T_CONF_SMS")
public class SMSConfiguration extends BaseElement implements Serializable,Comparable<SMSConfiguration>{

    @Predicate(label = "inbound.interval",type = Integer.class,search = true)
    private Integer inboundinterval =60;
    
    @Predicate(label = "outbound.interval",type = Integer.class,search = true)
    private Integer outboundinterval = 10;
    
    @Predicate(label = "delete.after.process",search = true,target = "combobox",values = "yes;no")
    private String deleteafterprocess = "0";
    
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "SMSCONF_ID")
    @Predicate(label = " ",type = SMSGateway.class,target = "one-to-many",group = true,groupName = "group1",groupLabel = "modem.configuration")
    private List<SMSGateway> modems = new ArrayList<SMSGateway>();

    public SMSConfiguration() {
    }
    
    public SMSConfiguration(SMSConfiguration entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.inboundinterval = entity.inboundinterval;
        this.outboundinterval = entity.outboundinterval;
        this.deleteafterprocess = entity.deleteafterprocess;
    }

    public Integer getInboundinterval() {
        return inboundinterval;
    }

    public void setInboundinterval(Integer inboundinterval) {
        this.inboundinterval = inboundinterval;
    }

    public Integer getOutboundinterval() {
        return outboundinterval;
    }

    public void setOutboundinterval(Integer outboundinterval) {
        this.outboundinterval = outboundinterval;
    }

    public String getDeleteafterprocess() {
        return deleteafterprocess;
    }

    public void setDeleteafterprocess(String deleteafterprocess) {
        this.deleteafterprocess = deleteafterprocess;
    }

    public List<SMSGateway> getModems() {
        return modems;
    }

    public void setModems(List<SMSGateway> modems) {
        this.modems = modems;
    }

    @Override
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "parameters"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "parametrage";//To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(SMSConfiguration o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
