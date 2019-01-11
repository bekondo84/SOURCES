
package com.core.langues;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.TimedObject;
import javax.ejb.Timer;

@TransactionAttribute
@Stateless(mappedName = "LangueManager")
public class LangueManagerImpl
    extends AbstractGenericManager<Langue, Long>
    implements LangueManagerLocal, LangueManagerRemote,TimedObject
{

    @EJB(name = "LangueDAO")
    protected LangueDAOLocal dao;

    @Resource
    SessionContext context ;
    
    public LangueManagerImpl() {
    }

    @Override
    public GenericDAO<Langue, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    @Override
    public void ejbTimeout(Timer timer) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void scheduleEventManager(Date initialExpiration, long duration) {
        //To change body of generated methods, choose Tools | Templates.
        context.getTimerService().createTimer(initialExpiration, duration, "Event schulder ...");
    }
    
    

}
