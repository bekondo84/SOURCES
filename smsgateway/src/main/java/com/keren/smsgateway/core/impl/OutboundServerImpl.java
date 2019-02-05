/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.core.impl;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.keren.smsgateway.core.ifaces.OutBoundServer;
import com.keren.smsgateway.dao.ifaces.SMSConfigurationDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSINDAOLocal;
import com.keren.smsgateway.dao.ifaces.SMSOUTDAOLocal;
import com.keren.smsgateway.model.RoundLoadBalance;
import com.keren.smsgateway.model.SMSConfiguration;
import com.keren.smsgateway.model.SMSGateway;
import com.keren.smsgateway.model.SMSOUT;
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
import org.smslib.GatewayException;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author BEKO
 */
@TransactionAttribute
@Stateless(mappedName = "OutBoundServer")
public class OutboundServerImpl implements OutBoundServer , TimedObject{
    
    @EJB(name = "SMSConfigurationDAO")
    protected SMSConfigurationDAOLocal configdao;

    @EJB(name = "SMSOUTDAO")
    protected SMSOUTDAOLocal smsdao;
    
    @Resource
    SessionContext context ;

    /**
     * 
     */
    public OutboundServerImpl() {
    }
    
    
   
    @Override
    public void ejbTimeout(Timer timer) {
        //To change body of generated methods, choose Tools | Templates.
        List<SerialModemGateway> gateways = getSerialsGateway();
        for(SerialModemGateway modem:gateways){
            MessagesWriter reader = new MessagesWriter();
            try {
                reader.doIt(modem);
            } catch (Exception ex) {
                Logger.getLogger(InboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//end for(SerialModemGateway modem:gateways){
    }
      
    
    @Override
    public void scheduleEventManager(Date initialExpiration, long duration) {
         context.getTimerService().createTimer(initialExpiration, duration, "Event schulder ..."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    class MessagesWriter {

        public MessagesWriter() {
        }
        
        public void doIt(SerialModemGateway modem){
            try {
                OutboundNotification outboundNotification = new OutboundNotification();
                Service.getInstance().setOutboundMessageNotification(outboundNotification);
                Service.getInstance().addGateway(modem);
               Service.getInstance().setLoadBalancer(new RoundLoadBalance());
               Service.getInstance().startService();
               List<SMSOUT> messages = getMessagesToSend();
               for(SMSOUT msge : messages){
                   OutboundMessage outbound = new OutboundMessage(msge.getRecipient(), msge.getText());
                   outbound.setId(Long.toString(msge.getId()));
                    try {
                        Service.getInstance().sendMessage(outbound);
                    } catch (TimeoutException ex) {//Echec d'envoi du message
                        msge.setStatus("F");
                        Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        msge.setStatus("F");
                        Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        msge.setStatus("F");
                        Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    smsdao.update(msge.getId(), msge);
               }//end for(SMSOUT msge : messages){
               Service.getInstance().stopService();
            } catch (GatewayException ex) {
                Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SMSLibException ex) {
                Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(OutboundServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /**
     * 
     */
    public class OutboundNotification implements IOutboundMessageNotification
        {
                /**
                 * 
                 * @param gateway
                 * @param msg 
                 */
                @Override
                public void process(AGateway gateway, OutboundMessage msg)
                {   
                        SMSOUT outbound = smsdao.findByPrimaryKey("id", msg.getId());
                        outbound.setSentdate(msg.getDate());
                        outbound.setType(msg.getType().equals(Message.MessageTypes.WAPSI) ? "W" : "O");
                        outbound.setEncoding(msg.getEncoding().equals(Message.MessageEncodings.ENC7BIT) ? "7":(msg.getEncoding().equals(Message.MessageEncodings.ENC8BIT) ? "8":"U"));
                        outbound.setSrcport(msg.getSrcPort());
                        outbound.setDstport(msg.getDstPort());
                        outbound.setRefno(msg.getRefNo());
                        outbound.setPriority(msg.getPriority());
                        outbound.setStatus("S");
                        System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
                        System.out.println(msg);
                }
        }

    /**
     * 
     * @return 
     */
    private List<SMSOUT> getMessagesToSend(){
        List<SMSOUT> datas = new ArrayList<SMSOUT>();
        RestrictionsContainer container = RestrictionsContainer.newInstance();
        container.addEq("status", "U");
        List<SMSOUT> unsend = smsdao.filter(container.getPredicats(), null, null, 0, -1);
        if(unsend!=null && !unsend.isEmpty()){
            datas.addAll(unsend);
        }//end if(unsend!=null && !unsend.isEmpty()){
        container = RestrictionsContainer.newInstance();
        container.addEq("status", "Q");
        unsend = smsdao.filter(container.getPredicats(), null, null, 0, -1);
        if(unsend!=null && !unsend.isEmpty()){
            datas.addAll(unsend);
        }//end if(unsend!=null && !unsend.isEmpty()){
        return datas;
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

}
