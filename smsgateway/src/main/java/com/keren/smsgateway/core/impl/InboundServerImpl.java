/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.core.impl;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.keren.smsgateway.core.ifaces.InboundServer;
import com.keren.smsgateway.dao.ifaces.SMSConfigurationDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSINDAOLocal;
import com.keren.smsgateway.model.SMSConfiguration;
import com.keren.smsgateway.model.SMSGateway;
import com.keren.smsgateway.model.SMSIN;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TimedObject;
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.GatewayException;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.Message.MessageTypes;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author BEKO
 */
@TransactionAttribute
@Stateless(mappedName = "InboundServer")
public class InboundServerImpl implements InboundServer , TimedObject{
    
    @EJB(name = "SMSConfigurationDAO")
    protected SMSConfigurationDAOLocal configdao;
    
    @EJB(name = "SMSINDAO")
    protected SMSINDAOLocal smsdao;
    
    @Resource
    SessionContext context ;

    @Override
    public void ejbTimeout(Timer timer) {
        //To change body of generated methods, choose Tools | Templates.
        List<SerialModemGateway> gateways = getSerialsGateway();
        for(SerialModemGateway modem:gateways){
            MessagesReader reader = new MessagesReader();
            try {
                reader.doIt(modem);
            } catch (Exception ex) {
                Logger.getLogger(InboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//end for(SerialModemGateway modem:gateways){
    }
    
    
   
    
    /**
     * 
     * @return 
     */
    private List<SerialModemGateway> getSerialsGateway(){
        List<SerialModemGateway> gateways = new ArrayList<SerialModemGateway>();
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        List<SMSConfiguration> configs = configdao.filter(container.getPredicats(), null, null, 0,-1);
        if(configs==null||configs.isEmpty()){
            return gateways;
        }//end if(configs==null||configs.isEmpty()){
        SMSConfiguration configuration = configs.get(0);
        for(SMSGateway smsgate:configuration.getModems()){
            SerialModemGateway modem = new SerialModemGateway(smsgate.getType(), smsgate.getPort(), smsgate.getBaudrate(), smsgate.getManufacturer(), smsgate.getSerial());
            if(smsgate.getProtocol()==null||smsgate.getProtocol().trim().equalsIgnoreCase("0")){
                modem.setProtocol(AGateway.Protocols.PDU);
            }else{
                modem.setProtocol(AGateway.Protocols.TEXT);
            }//end if(smsgate.getProtocol()==null||smsgate.getProtocol().trim().equalsIgnoreCase("0")){
            modem.setInbound(smsgate.getInbound());
            modem.setOutbound(smsgate.getOutbound());
            modem.setSimPin(smsgate.getPin());
            gateways.add(modem);
        }//end for(SMSGateway smsgate:configuration.getModems()){
        return gateways;
    }

    @Override
    public void scheduleEventManager(Date initialExpiration, long duration) {
        //To change body of generated methods, choose Tools | Templates.
        context.getTimerService().createTimer(initialExpiration, duration, "Event schulder ...");
    }
    
    class MessagesReader{
        
        
         /**
          * 
          * @param modem
          * @throws TimeoutException
          * @throws IOException
          * @throws InterruptedException 
          */
        public void doIt(SerialModemGateway modem) throws Exception{
            try {
                //List of message that will hold the read message
                List<InboundMessage> msgList  ;
                //Creation of the notification callback for inbound and status response msge
                InboundNotification inboundNotification = new InboundNotification();
                // Create the notification callback method for inbound voice calls.
                CallNotification callNotification = new CallNotification();
                //Create the notification callback method for gateway statuses.
                GatewayStatusNotification statusNotification = new GatewayStatusNotification();
                OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
                //Set up the notification method 
                Service.getInstance().setInboundMessageNotification(inboundNotification);
                Service.getInstance().setCallNotification(callNotification);
                Service.getInstance().setGatewayStatusNotification(statusNotification);
                Service.getInstance().setOrphanedMessageNotification(orphanedMessageNotification);
                // Add the Gateway to the Service object.
                Service.getInstance().addGateway(modem);
                // Read Messages. The reading is done via the Service object and
                // affects all Gateway objects defined. This can also be more directed to a specific
                // Gateway - look the JavaDocs for information on the Service method calls.
                msgList = new ArrayList<InboundMessage>();
                Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.UNREAD);
                for(InboundMessage msg:msgList){
                    SMSIN inboundmsge = new SMSIN(msg.getOriginator(),msg.getDate(), new Date(), msg.getText(), Integer.toString(msg.getMpRefNo()), msg.getGatewayId());
                    inboundmsge.setType(msg.getType().equals(MessageTypes.STATUSREPORT) ? "S":"I");
                    inboundmsge.setEncoding(msg.getEncoding().equals(Message.MessageEncodings.ENC7BIT) ? "7":(msg.getEncoding().equals(Message.MessageEncodings.ENC8BIT) ? "8":"U"));
                    smsdao.save(inboundmsge);
                }//end for(InboundMessage msg:msgList){
            } catch (GatewayException ex) {
                Logger.getLogger(InboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TimeoutException ex) {
                Logger.getLogger(InboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(InboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(InboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                Service.getInstance().stopService();
            }
        }
    }
    class InboundNotification implements IInboundMessageNotification{

        @Override
        public void process(AGateway gateway, Message.MessageTypes msgType, InboundMessage msg) {
            //To change body of generated methods, choose Tools | Templates.
            if (msgType == MessageTypes.INBOUND) {
                            
                System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
            System.out.println("samyan Qayyum Wahla "+ msg.getOriginator());
            }
            else if (msgType == MessageTypes.STATUSREPORT) System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
        }//end public void process(AGateway gateway, Message.MessageTypes msgType, InboundMessage msg) {
        
    }
    
      public class CallNotification implements ICallNotification
        {
                @Override
                public void process(AGateway gateway, String callerId)
                {
                        System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
                }
        }

        public class GatewayStatusNotification implements IGatewayStatusNotification
        {
                @Override
                public void process(AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus)
                {
                        System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
                }
        }

        public class OrphanedMessageNotification implements IOrphanedMessageNotification
        {
                @Override
                public boolean process(AGateway gateway, InboundMessage msg)
                {
                        System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
                        System.out.println(msg);
                        // Since we are just testing, return FALSE and keep the orphaned message part.
                        return false;
                }
        }
}
