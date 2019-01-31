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
import javax.persistence.TemporalType;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_OUT_SMS")
public class SMSOUT extends BaseElement implements Serializable,Comparable<SMSOUT>{

    /**
     * The message type. This should be "O" for normal outbound messages, or "W" for wap si messages.
     */
    @Column(nullable = false)
    @Predicate(label = "type",search = true,optional = false)
    private String type ;
    
    /**
     * The recipient's number to whom the message should be sent. International format, no leading "+" or zeroes.
     */
    @Column(nullable = false)
    @Predicate(label = "recipient",search = true,optional = false)
    private String recipient;
    
    /**
     * The message text.
     */
    @Column(nullable = false)
    @Predicate(label = "text",optional = false,search = true)
    private String text ;

    /**
     * The WAP SI URL address.
     */
    @Column(name = "wap_url")
    @Predicate(label = "wap.url",search = true)
    private String wapurl;
    
    /**
     * The WAP SI expiry date. If no value is given, SMSServer will calculate a 7 day ahead expiry date.
     */
    @Column(name = "wap_expiry_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Predicate(label = "wap.expiry.date",type = Date.class,target = "datetime",search = true)
    private Date wapexpiredate ;
    
    /**
     * The WAP SI signal. Use "N" for NONE, "L" for LOW, "M" for MEDIUM, "H" for HIGH, "D" for DELETE. If no value/invalid value is given, the NONE signal will be used.
     */
    @Column(name = "wap_signal")
    @Predicate(label = "wap.signal",search = true)
    private String wapsignal;
    
    /**
     * The datetime when this record was created.
     */
    @Column(nullable = false,name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    @Predicate(label = "create.date",type = Date.class,target = "datetime",search = true)
    private Date createdate =new Date();
    
    /**
     * The originator. Normally you should leave this blank.
     */
    @Column(nullable = false)
    @Predicate(label = "originator",optional = true,search = true)
    private String originator ="";
    
    /**
     * "7" for 7bit, "8" for 8bit and "U" for Unicode/UCS2.
     */
    @Column(nullable = false)
    @Predicate(label = "encoding",optional = false,search = true)
    private String encoding="7";
    
    /**
     * Set to 1 if you require a status report message to be generated.
     */
    @Column(nullable = false,name = "status_report")
    @Predicate(label = "status.report",type = Integer.class,optional = false,search = true)
    private Integer statusreport =0 ;
    
    /**
     * Set to 1 if you require your message to be sent as a flash message.
     */
    @Column(nullable = false,name = "flash_sms")
    @Predicate(label = "flash.sms",type = Integer.class,optional = false,search = true)
    private Integer flashsms =0;
    
    /**
     * Set to source port (for midlets)
     */
    @Column(name = "src_port",nullable = false)
    @Predicate(label = "src.port",optional = false,type = Integer.class,search = true)
    private Integer srcport = -1;
    
    /**
     * Set to destination port (for midlets)
     */
    @Column(name = "dst_port",nullable = false)
    @Predicate(label = "dst.port",optional = false,type = Integer.class,search = true)
    private Integer dstport = -1 ;
    
    /**
     * The sent date. This field is updated by SMSServer when it sends your message.
     */
    @Column(name = "sent_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Predicate(label = "sent.date",type = Date.class,target = "datetime",search = true)
    private Date sentdate ;
    
    /**
     * The Reference ID of your message. This field is updated by SMSServer when it sends your message.
     */
    @Column(name = "ref_no")
    @Predicate(label = "ref.no",search = true)
    private String refno ;
    
    /**
     * Lower (or negative) values mean lower priority than higher (or positive) values. By convention, a priority of a value 0 (zero) is considered the normal priority. High priority messages get sent first than others.
     */
    @Column(nullable = false)
    @Predicate(label = "priority",type = Integer.class,optional = false,search = true)
    private Integer priority = 0;
    
    /**
     * "U" : unsent, "Q" : queued, "S" : sent, "F" : failed. This field is updated by SMSServer when it sends your message. If set in the configuration file, this field takes extra values depending on the received status report message: "D" : delivered, "P" : pending, "A" : aborted.
     */
    @Column(nullable = false)
    @Predicate(label = "status",optional = false,search = true)
    private String status ="U";
    
    /**
     * The number of retries SMSServer did to send your message. This field is updated by SMSServer.
     */
    @Column(nullable = false)
    @Predicate(label = "errors",type = Integer.class,optional = false,search = true)
    private Integer errors = 0;
    
    /**
     * Set it to the star character if you want to leave to SMSServer the decision as to which gateway to use to send your message. Set it to a specific Gateway ID to force SMSServer to send your message via this gateway.
     */
    @Column(name = "gateway_id")
    @Predicate(label = "gateway.id",search = true)
    private String gateway;

    /**
     * 
     */
    public SMSOUT() {
    }

    /**
     * 
     * @param type
     * @param recipient
     * @param text
     * @param wapurl
     * @param wapexpiredate
     * @param sentdate
     * @param refno
     * @param gateway
     * @param id
     * @param designation
     * @param moduleName
     * @param comparedid 
     */
    public SMSOUT(String type, String recipient, String text, String wapurl, Date wapexpiredate, Date sentdate, String refno, String gateway, long id, String designation, String moduleName, long comparedid) {
        super(id, designation, moduleName, comparedid);
        this.type = type;
        this.recipient = recipient;
        this.text = text;
        this.wapurl = wapurl;
        this.wapexpiredate = wapexpiredate;
        this.sentdate = sentdate;
        this.refno = refno;
        this.gateway = gateway;
    }
    
     public SMSOUT(SMSOUT entity) {
        super(entity.id, entity.designation, entity.moduleName, entity.compareid);
        this.type = entity.type;
        this.recipient = entity.recipient;
        this.text = entity.text;
        this.wapurl = entity.wapurl;
        this.wapexpiredate = entity.wapexpiredate;
        this.sentdate = entity.sentdate;
        this.refno = entity.refno;
        this.gateway = entity.gateway;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWapurl() {
        return wapurl;
    }

    public void setWapurl(String wapurl) {
        this.wapurl = wapurl;
    }

    public Date getWapexpiredate() {
        return wapexpiredate;
    }

    public void setWapexpiredate(Date wapexpiredate) {
        this.wapexpiredate = wapexpiredate;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public Integer getStatusreport() {
        return statusreport;
    }

    public void setStatusreport(Integer statusreport) {
        this.statusreport = statusreport;
    }

    public Integer getFlashsms() {
        return flashsms;
    }

    public void setFlashsms(Integer flashsms) {
        this.flashsms = flashsms;
    }

    public Integer getSrcport() {
        return srcport;
    }

    public void setSrcport(Integer srcport) {
        this.srcport = srcport;
    }

    public Integer getDstport() {
        return dstport;
    }

    public void setDstport(Integer dstport) {
        this.dstport = dstport;
    }

    public Date getSentdate() {
        return sentdate;
    }

    public void setSentdate(Date sentdate) {
        this.sentdate = sentdate;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getErrors() {
        return errors;
    }

    public void setErrors(Integer errors) {
        this.errors = errors;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    @Override
    public String getOwnerentity() {
        return "smsout"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesableupdate() {
        return !status.trim().equalsIgnoreCase("U"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDesabledelete() {
        return super.isDesabledelete(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCreateonfield() {
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDesignation() {
        return recipient; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "smsgateway"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getListTitle() {
        return "sms.out.list"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "sms.out.detail"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public int compareTo(SMSOUT o) {
        return 0 ;//To change body of generated methods, choose Tools | Templates.
    }
    
}
