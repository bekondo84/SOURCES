
package com.keren.smsgateway.jaxrs.impl;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import javax.ws.rs.Path;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.google.gson.Gson;
import com.kerem.core.MetaDataUtil;
import com.keren.smsgateway.core.ifaces.GroupeContactManagerRemote;
import com.keren.smsgateway.core.ifaces.ProgramSMSManagerRemote;
import com.keren.smsgateway.jaxrs.ifaces.ProgramSMSRS;
import com.keren.smsgateway.model.Contact;
import com.keren.smsgateway.model.GroupeContact;
import com.keren.smsgateway.model.ProgramSMS;
import com.megatimgroup.generic.jax.rs.layer.annot.Manager;
import com.megatimgroup.generic.jax.rs.layer.impl.AbstractGenericService;
import com.megatimgroup.generic.jax.rs.layer.impl.MetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;


/**
 * Classe d'implementation du Web Service JAX-RS

 * @since Fri Feb 01 10:56:17 WAT 2019
 * 
 */
@Path("/programsms")
public class ProgramSMSRSImpl
    extends AbstractGenericService<ProgramSMS, Long>
    implements ProgramSMSRS
{

    /**
     * On injecte un Gestionnaire d'entites
     * 
     */
    @Manager(application = "smsgateway", name = "ProgramSMSManagerImpl", interf = ProgramSMSManagerRemote.class)
    protected ProgramSMSManagerRemote manager;
    
    @Manager(application = "smsgateway", name = "GroupeContactManagerImpl", interf = GroupeContactManagerRemote.class)
    protected GroupeContactManagerRemote groupemanager;

    public ProgramSMSRSImpl() {
        super();
    }

    /**
     * Methode permettant de retourner le gestionnaire d'entites
     * 
     */
    @Override
    public GenericManager<ProgramSMS, Long> getManager() {
        return manager;
    }

    public String getModuleName() {
        return ("smsgateway");
    }

    @Override
    public MetaData getMetaData(HttpHeaders headers) {
        MetaData meta =null;
        try {
            meta = MetaDataUtil.getMetaData(new ProgramSMS(), new HashMap<String, MetaData>(), new ArrayList<String>());
        } catch (InstantiationException ex) {
            Logger.getLogger(ProgramSMSRSImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProgramSMSRSImpl.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
}
