
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.ModeReglementManagerLocal;
import com.basaccount.core.ifaces.achats.ModeReglementManagerRemote;
import com.basaccount.dao.ifaces.achats.ModeReglementDAOLocal;
import com.basaccount.model.achats.ModeReglement;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerem.core.KerenExecption;
import com.megatim.common.annotations.OrderType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@TransactionAttribute
@Stateless(mappedName = "ModeReglementManager")
public class ModeReglementManagerImpl
    extends AbstractGenericManager<ModeReglement, Long>
    implements ModeReglementManagerLocal, ModeReglementManagerRemote
{

    @EJB(name = "ModeReglementDAO")
    protected ModeReglementDAOLocal dao;

    public ModeReglementManagerImpl() {
    }

    @Override
    public GenericDAO<ModeReglement, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public List<ModeReglement> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties, int firstResult, int maxResult) {
        List<ModeReglement> datas = super.filter(predicats, orders, properties, firstResult, maxResult); //To change body of generated methods, choose Tools | Templates.
        List<ModeReglement> result = new ArrayList<ModeReglement>();
        for(ModeReglement data:datas){
            result.add(new ModeReglement(data));
        }
        return result;
    }

    @Override
    public ModeReglement find(String propertyName, Long entityID) {
        ModeReglement data = super.find(propertyName, entityID); //To change body of generated methods, choose Tools | Templates.
        ModeReglement result = new ModeReglement(data);
        return result;
    }

    @Override
    public ModeReglement delete(Long id) {
        ModeReglement data = super.delete(id); //To change body of generated methods, choose Tools | Templates.
        return new ModeReglement(data);
    }

    @Override
    public void processBeforeUpdate(ModeReglement entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("code.reglement.required");
        }else if(entity.getCompte()==null){
            throw new KerenExecption("compte.required");
        }
        super.processBeforeUpdate(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processBeforeSave(ModeReglement entity) {
        if(entity.getCode()==null||entity.getCode().trim().isEmpty()){
            throw new KerenExecption("code.reglement.required");
        }else if(entity.getCompte()==null){
            throw new KerenExecption("compte.required");
        }
        super.processBeforeSave(entity); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
