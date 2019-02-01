
package com.keren.smsgateway.core.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.keren.smsgateway.core.ifaces.GroupeContactManagerLocal;
import com.keren.smsgateway.core.ifaces.GroupeContactManagerRemote;
import com.keren.smsgateway.dao.ifaces.GroupeContactDAOLocal;
import com.keren.smsgateway.model.Contact;
import com.keren.smsgateway.model.GroupeContact;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "GroupeContactManager")
public class GroupeContactManagerImpl
    extends AbstractGenericManager<GroupeContact, Long>
    implements GroupeContactManagerLocal, GroupeContactManagerRemote
{

    @EJB(name = "GroupeContactDAO")
    protected GroupeContactDAOLocal dao;

    public GroupeContactManagerImpl() {
    }

    @Override
    public GenericDAO<GroupeContact, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<GroupeContact> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<GroupeContact> datas = dao.filter(predicats, orders, properties, firstResult, maxResult);
        List<GroupeContact> result = new ArrayList<GroupeContact>();
        for(GroupeContact data:datas){
            result.add(new GroupeContact(data));
        }
        return result; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupeContact find(String propertyName, Long entityID) {
        GroupeContact groupe = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        GroupeContact result = new GroupeContact(groupe);
        for(Contact cont:groupe.getContacts()){
            result.getContacts().add(cont);
        }
        return result;
    }

    @Override
    public GroupeContact delete(Long id) {
        GroupeContact data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new GroupeContact(data);
    }
    
    

}
