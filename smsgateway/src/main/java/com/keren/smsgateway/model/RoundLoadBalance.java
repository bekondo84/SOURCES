/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.model;

import java.util.Collection;
import org.smslib.AGateway;
import org.smslib.OutboundMessage;
import org.smslib.balancing.LoadBalancer;

/**
 *
 * @author BEKO
 */
public class RoundLoadBalance extends LoadBalancer{

    @Override
    public AGateway balance(OutboundMessage msg, Collection<AGateway> candidates) {
        return super.balance(msg, candidates); //To change body of generated methods, choose Tools | Templates.
    }
    
}
