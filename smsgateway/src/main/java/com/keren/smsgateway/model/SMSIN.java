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
@Table(name = "T_IN_SMS")
public class SMSIN extends BaseElement implements Serializable,Comparable<SMSIN>{

    @Column(nullable = false)
    @Predicate(label = "process",type = Integer.class,search = true)
    private Integer process =0 ;
    
    @Column(nullable = false)
    @Predicate(label = "originator",search = true,optional = false)
    private String originator;
    /**
     * I :for inbound message and S for status report message
     */
    @Column(nullable = false)
    @Predicate(label = "type",search = true,optional = false)
    private String type ;
    
    /**
     *  7 : for 7 bits , 8:for 8bits and U:for Unicode/UCS2
     */
    @Column(nullable = false)
    @Predicate(label = "encoding",search = true,optional = false)
    private String encoding;
    
    /**
     * the message date(retrieved by the message header
     */
    @Column(name = "message_date",nullable = false)
    @Predicate(label = "message.date",type = Date.class,target = "datetime",search = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date messageDate ;
    
    /**
     * The datetime when message was receive
     */
    @Column(name = "receive_date",nullable = false)
    @Predicate(label = "recieve.date",type = Date.class,target = "datetime",search = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date recieveDate ;
    
    /**
     * the body of the message
     */
    @Predicate(label = " ",target = "textarea",search = true,group = true,groupName = "group1",groupLabel = "text")
    private String text ;
    
    /**
     * Available only for status S :reference of the original outbound messsage 
     */
    @Column(name = "original_ref_no")
    @Predicate(label = "original.ref.number",search = true)
    private String refNo;
    
    /**
     * Available only for status report messages: refers to the receive date of the original outbound message.
     */
    @Column(name = "original_receive_date")
    @Predicate(label = "original.receive.date",search = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date receiveDate;
    
    /**
     * The ID of the gateway from which the message was received.
     */
    @Column(name = "gateway_id")
    @Predicate(label = "gateway.id",search = true)
    private String gateway;

    public SMSIN() {
    }

    /**
     * 
     * @param originator
     * @param type
     * @param encoding
     * @param messageDate
     * @param recieveDate
     * @param text
     * @param refNo
     * @param receiveDate
     * @param gateway
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public SMSIN(String originator, String type, String encoding, Date messageDate, Date recieveDate, String text, String refNo, Date receiveDate, String gateway, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.originator = originator;
        this.type = type;
        this.encoding = encoding;
        this.messageDate = messageDate;
        this.recieveDate = recieveDate;
        this.text = text;
        this.refNo = refNo;
        this.receiveDate = receiveDate;
        this.gateway = gateway;
    }
    
    /**
     * 
     * @param entity 
     */
     public SMSIN(SMSIN entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.originator = entity.originator;
        this.type = entity.type;
        this.encoding = entity.encoding;
        this.messageDate = entity.messageDate;
        this.recieveDate = entity.recieveDate;
        this.text = entity.text;
        this.refNo = entity.refNo;
        this.receiveDate = entity.receiveDate;
        this.gateway = entity.gateway;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public Date getRecieveDate() {
        return recieveDate;
    }

    public void setRecieveDate(Date recieveDate) {
        this.recieveDate = recieveDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String getOwnerentity() {
        return "smsin"; //To change body of generated methods, choose Tools | Templates.
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
        return originator; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "sms.in.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "sms.in.detail"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
     
     
    @Override
    public int compareTo(SMSIN o) {
        return 0 ;//To change body of generated methods, choose Tools | Templates.
    }
    
}
