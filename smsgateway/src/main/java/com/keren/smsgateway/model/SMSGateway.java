/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.model;

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
@Table(name = "T_GWY_SMS")
public class SMSGateway extends BaseElement implements Serializable,Comparable<SMSGateway>{
    
    @Predicate(label = "modem.type",search = true,optional = false)
    private String type ="modem1 , SerialModem";
    
    @Predicate(label = "modem.com",search = true,optional = false)
    private String port ="COM1";
    
    @Predicate(label = "modem.bandrate",type = Integer.class,search = true,optional = false)
    private Integer baudrate=57600;
    
    @Predicate(label = "modem.manufacturer",search = true)
    private String manufacturer ="Nokia";
    
    @Predicate(label = "modem.model",search = true)
    private String model = "6310i";
    
    @Predicate(label = "modem.protocol",target = "combobox",values = "PDU;TEXT",search = true)
    private String protocol = "0";
    
    @Predicate(label = "modem.pin",search = true)
    private String pin ="0000";
    
    @Predicate(label = "modem.inbound",type = Boolean.class,search = true)
    private Boolean inbound = Boolean.TRUE;
    
    @Predicate(label = "modem.outbound",type = Boolean.class,search = true)
    private Boolean outbound = Boolean.FALSE;
    
   @Predicate(label = "modem.smscnumber",optional = false,search = true)
    private String smscnumber ;
    
   @Predicate(label = "modem.initstring",search = true)
    private String initstring ="ATZ\\rATZ\\rATZ\\r";

    public SMSGateway() {
    }

    /**
     * 
     * @param smscnumber
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public SMSGateway(String smscnumber, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.smscnumber = smscnumber;
    }
    
    public SMSGateway(SMSGateway entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.type = entity.type;
        this.port = entity.port;
        this.baudrate = entity.baudrate;
        this.manufacturer = entity.manufacturer;
        this.model = entity.model;
        this.protocol = entity.protocol;
        this.pin = entity.pin;
        this.inbound = entity.inbound;
        this.outbound=entity.outbound;
        this.smscnumber = entity.smscnumber;
        this.initstring = entity.initstring;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(Integer baudrate) {
        this.baudrate = baudrate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

   

    public Boolean getInbound() {
        return inbound;
    }

    public void setInbound(Boolean inbound) {
        this.inbound = inbound;
    }

    public Boolean getOutbound() {
        return outbound;
    }

    public void setOutbound(Boolean outbound) {
        this.outbound = outbound;
    }

    public String getSmscnumber() {
        return smscnumber;
    }

    public void setSmscnumber(String smscnumber) {
        this.smscnumber = smscnumber;
    }

    public String getInitstring() {
        return initstring;
    }

    public void setInitstring(String initstring) {
        this.initstring = initstring;
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
    public String getListTitle() {
        return super.getListTitle(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "sms.gateway"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public int compareTo(SMSGateway o) {
        //To change body of generated methods, choose Tools | Templates.
        return smscnumber.compareTo(o.smscnumber);
    }
    
}
