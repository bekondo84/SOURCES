/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.model;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_CALL_SMS")
public class SMSCALL extends BaseElement implements Serializable,Comparable<SMSCALL>{

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false,name = "call_date")
    @Predicate(label = "call.date",type = Date.class,target = "datetime",search = true)
    private Date calldate;
    
    @Column(name = "gateway_id",nullable = false)
    @Predicate(label = "gateway.id",search = true)
    private String gateway;
    
    @Column(name = "caller_id",nullable = false)
    @Predicate(label = "caller.id",search = true)
    private String caller ;

    /**
     * 
     */
    public SMSCALL() {
    }

    /**
     * 
     * @param calldate
     * @param gateway
     * @param caller
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public SMSCALL(Date calldate, String gateway, String caller, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.calldate = calldate;
        this.gateway = gateway;
        this.caller = caller;
    }
    
    public SMSCALL(SMSCALL entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.calldate = entity.calldate;
        this.gateway = entity.gateway;
        this.caller = entity.caller;
    }

    public Date getCalldate() {
        return calldate;
    }

    public void setCalldate(Date calldate) {
        this.calldate = calldate;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    @Override
    public String getOwnerentity() {
        return "smscall"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
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
    public String getDesignation() {
        return super.getDesignation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "smsgateway";  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return super.getEditTitle(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(SMSCALL o) {
        //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
}
