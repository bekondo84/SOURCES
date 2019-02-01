
package com.keren.smsgateway.jaxrs.impl;

import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.google.gson.Gson;
import com.kerem.core.MetaDataUtil;
import com.keren.smsgateway.core.ifaces.GroupeContactManagerRemote;
import com.keren.smsgateway.core.ifaces.SMSOUTManagerRemote;
import com.keren.smsgateway.core.ifaces.SendSMSManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.SendSMSRS;
import com.keren.smsgateway.model.Contact;
import com.keren.smsgateway.model.GroupeContact;
import com.keren.smsgateway.model.SMSOUT;
import com.keren.smsgateway.model.SendSMS;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Feb 01 10:34:35 WAT 2019
 * 
 */
@Path("/sendsms")
public class SendSMSRSImpl
    extends AbstractGenericService<SendSMS, Long>
    implements SendSMSRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "smsgateway", name = "SendSMSManagerImpl", interf = SendSMSManagerRemote.class)
    protected SendSMSManagerRemote manager;
    
     @Manager(application = "smsgateway", name = "GroupeContactManagerImpl", interf = GroupeContactManagerRemote.class)
    protected GroupeContactManagerRemote groupemanager;
     
      @Manager(application = "smsgateway", name = "SMSOUTManagerImpl", interf = SMSOUTManagerRemote.class)
    protected SMSOUTManagerRemote smsmanager;
     

    public SendSMSRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<SendSMS, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("smsgateway");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta = null;
        try {
            meta = MetaDataUtil.getMetaData(new SendSMS(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(SendSMSRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SendSMSRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meta; //To change body of generated methods, choose Tools | Templates.
    }

    
     @Override
    public List<Contact> contacts(HttpHeaders headers) {
        Gson gson = new Gson();
        Long groupeid = gson.fromJson(headers.getRequestHeader("groupe").get(0), Long.class);
        GroupeContact groupe = groupemanager.find("id", groupeid);
        return groupe.getContacts();
    }

    @Override
    public SendSMS save(HttpHeaders headers, SendSMS entity) {
        List<SMSOUT> messages = convert(entity);
        smsmanager.save(messages);
        return entity; //To change body of generated methods, choose Tools | Templates.
    }
    
    /***
     * Convert entity in list of SMS
     * @param entity
     * @return 
     */
    private List<SMSOUT> convert(SendSMS entity){
        List<SMSOUT> messages = new ArrayList<SMSOUT>();
        List<String> phones = new ArrayList<String>();
        if(entity.getPhone()!=null && !entity.getPhone().isEmpty()){
            String[] numbers = entity.getPhone().split(";");
            for(String number:numbers){
                if(!number.trim().isEmpty()){
                    phones.add(number);
                }//end if(!number.trim().isEmpty()){
            }//end for(String number:numbers){
        }//end if(entity.getPhone()!=null && !entity.getPhone().isEmpty()){
        if(!entity.getContacts().isEmpty()){
            for(Contact contact : entity.getContacts()){
                if(contact.getMobile()!=null && !contact.getMobile().trim().isEmpty()){
                    if(!phones.contains(contact.getMobile())){
                        phones.add(contact.getMobile());
                    }//end if(!phones.contains(contact.getMobile())){
                }else if(contact.getTel()!=null && !contact.getTel().trim().isEmpty()){
                     if(!phones.contains(contact.getTel())){
                        phones.add(contact.getTel());
                    }//end if(!phones.contains(contact.getMobile())){
                }//end if(contact.getMobile()!=null && !contact.getMobile().trim().isEmpty()){
            }//end for(Contact contact : entity.getContacts()){
        }//end if(!entity.getContacts().isEmpty()){
        int index =0;
        for(String phone : phones){
            SMSOUT sms = new SMSOUT(phone, entity.getText());
            Date now = new Date();
            sms.setCompareid(now.getTime()+index);index++;
            messages.add(sms);
        }//end for(String phone : phones){
        return messages;
    }
    
}
