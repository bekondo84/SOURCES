/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.core.impl;

import com.keren.smsgateway.core.ifaces.SMSServer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.Group;
import org.smslib.ICallNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.IQueueSendingNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;
import org.smslib.OutboundMessage;
import org.smslib.Settings;
import org.smslib.balancing.LoadBalancer;
import org.smslib.crypto.KeyManager;
import org.smslib.modem.SerialModemGateway;
import org.smslib.routing.Router;

/**
 *
 * @author BEKO
 */
public class SMSServerImpl implements SMSServer,TimedObject{

    @Override
    public void ejbTimeout(Timer timer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class InboundNotification implements IInboundMessageNotification
	{
		@Override
                public void process(AGateway ag, MessageTypes mt, InboundMessage im) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
	}

    
    class OutboundNotification implements IOutboundMessageNotification
    {
            @Override
            public void process(AGateway ag, OutboundMessage om) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    }

    class CallNotification implements ICallNotification
    {
        @Override
        public void process(AGateway ag, String string) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

	class QueueSendingNotification implements IQueueSendingNotification
	{

            @Override
            public void process(AGateway ag, OutboundMessage om) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
		
	}

	public boolean checkPriorityTimeFrame(int priority)
	{
//		String timeFrame;
//		String from, to, current;
//		Calendar cal = Calendar.getInstance();
//		if (priority < 0) timeFrame = getProperties().getProperty("settings.timeframe.low", "0000-2359");
//		else if (priority == 0) timeFrame = getProperties().getProperty("settings.timeframe.normal", "0000-2359");
//		else if (priority >= 0) timeFrame = getProperties().getProperty("settings.timeframe.high", "0000-2359");
//		else timeFrame = "0000-2359";
//		from = timeFrame.substring(0, 4);
//		to = timeFrame.substring(5, 9);
//		cal.setTime(new java.util.Date());
//		current = cal.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + cal.get(Calendar.HOUR_OF_DAY) : "" + cal.get(Calendar.HOUR_OF_DAY);
//		current += cal.get(Calendar.MINUTE) < 10 ? "0" + cal.get(Calendar.MINUTE) : "" + cal.get(Calendar.MINUTE);
//		if ((Integer.parseInt(current) >= Integer.parseInt(from)) && (Integer.parseInt(current) < Integer.parseInt(to))) return true;
		return false;
	}
        
       public  class Service {
            
            private List<SerialModemGateway> gateways ;
            
            private Router router ;
            
            private LoadBalancer loadBalancer ;
            
            private IInboundMessageNotification inboundNotification;

            private IOutboundMessageNotification outboundNotification;

            private ICallNotification callNotification;

            private IQueueSendingNotification queueSendingNotification;

            private long startMillis;

            private Collection<Group> groups;

            private volatile ServiceStatus serviceStatus;

            private KeyManager keyManager;

            /**
             * Configuration settings.
             * 
             * @see Settings
             */
            private Settings S;

            public Service() {
            }
            
            

            public List<SerialModemGateway> getGateways() {
                return gateways;
            }

            public void setGateways(List<SerialModemGateway> gateways) {
                this.gateways = gateways;
            }

            public Router getRouter() {
                return router;
            }

            public void setRouter(Router router) {
                this.router = router;
            }

            public LoadBalancer getLoadBalancer() {
                return loadBalancer;
            }

            public void setLoadBalancer(LoadBalancer loadBalancer) {
                this.loadBalancer = loadBalancer;
            }

            public IInboundMessageNotification getInboundNotification() {
                return inboundNotification;
            }

            public void setInboundNotification(IInboundMessageNotification inboundNotification) {
                this.inboundNotification = inboundNotification;
            }

            public IOutboundMessageNotification getOutboundNotification() {
                return outboundNotification;
            }

            public void setOutboundNotification(IOutboundMessageNotification outboundNotification) {
                this.outboundNotification = outboundNotification;
            }

            public ICallNotification getCallNotification() {
                return callNotification;
            }

            public void setCallNotification(ICallNotification callNotification) {
                this.callNotification = callNotification;
            }

            public IQueueSendingNotification getQueueSendingNotification() {
                return queueSendingNotification;
            }

            public void setQueueSendingNotification(IQueueSendingNotification queueSendingNotification) {
                this.queueSendingNotification = queueSendingNotification;
            }

            public long getStartMillis() {
                return startMillis;
            }

            public void setStartMillis(long startMillis) {
                this.startMillis = startMillis;
            }

            public Collection<Group> getGroups() {
                return groups;
            }

            public void setGroups(Collection<Group> groups) {
                this.groups = groups;
            }

            public ServiceStatus getServiceStatus() {
                return serviceStatus;
            }

            public void setServiceStatus(ServiceStatus serviceStatus) {
                this.serviceStatus = serviceStatus;
            }

            public KeyManager getKeyManager() {
                return keyManager;
            }

            public void setKeyManager(KeyManager keyManager) {
                this.keyManager = keyManager;
            }

            public Settings getS() {
                return S;
            }

            public void setS(Settings S) {
                this.S = S;
            }
        
            /**
                * Adds a gateway to the list of gateways managed by the Service class. The
                * Service should be stopped in order to add gateways.
                * 
                * @param gateway
                *            The gateway to be added.
                * @throws GatewayException
                *             You tried to add a gateway while the Service is started.
                * @see #getGateways()
                */
               public void addGateway(SerialModemGateway gateway) throws GatewayException
               {
                       getGateways().add(gateway);
               }
           
        }//end public  class Service {
        public enum ServiceStatus{
                 STARTED, STOPPED
            }
    
}
