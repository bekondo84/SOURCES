
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.ProgramSMSManagerLocal;
import com.keren.smsgateway.core.ifaces.ProgramSMSManagerRemote;
import com.keren.smsgateway.dao.ifaces.ProgramSMSDAOLocal;
import com.keren.smsgateway.model.Contact;
import com.keren.smsgateway.model.ProgramSMS;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "ProgramSMSManager")
public class ProgramSMSManagerImpl
    extends AbstractGenericManager<ProgramSMS, Long>
    implements ProgramSMSManagerLocal, ProgramSMSManagerRemote
{

    @EJB(name = "ProgramSMSDAO")
    protected ProgramSMSDAOLocal dao;

    public ProgramSMSManagerImpl() {
    }

    @Override
    public GenericDAO<ProgramSMS, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<ProgramSMS> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<ProgramSMS> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<ProgramSMS> result = new ArrayList<ProgramSMS>();
        for(ProgramSMS data:datas){
            result.add(new ProgramSMS(data));
        }
        return result;
    }

    @Override
    public ProgramSMS find(String propertyName, Long entityID) {
        ProgramSMS data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        ProgramSMS result = new ProgramSMS(data);
        for(Contact con:data.getContacts()){
            result.getContacts().add(con);
        }
        return result;
    }

    @Override
    public ProgramSMS delete(Long id) {
        ProgramSMS data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new ProgramSMS(data);
    }
    
    

}
