/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.core.impl;

import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.keren.smsgateway.core.ifaces.InboundServer;
import com.keren.smsgateway.core.ifaces.OutBoundServer;
import com.keren.smsgateway.dao.ifaces.SMSConfigurationDAOLocal;
import com.keren.smsgateway.model.SMSConfiguration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author BEKO
 */

public class SMSgatewayStartUp {
    
    @EJB(name = "InboundServer")
    protected InboundServer inbloundManager;
    
    @EJB(name = "OutBoundServer")
    protected OutBoundServer outboundManager;
    
    @EJB(name = "SMSConfigurationDAO")
    protected SMSConfigurationDAOLocal configdao;
    
    
    @PostConstruct
    @SuppressWarnings("empty-statement")
    public void start(){
        List configs = configdao.filter(new ArrayList<Predicat>(), null, null, 0, -1);
        if(configs==null||configs.isEmpty()){
            return ;
        }//end if(configs==null||configs.isEmpty())
        SMSConfiguration config = (SMSConfiguration) configs.get(0);
        inbloundManager.scheduleEventManager(new Date(), config.getInboundinterval());
        outboundManager.scheduleEventManager(new Date(), config.getOutboundinterval());
    }
    
    @PreDestroy
    public void stop(){
        
    }
}
