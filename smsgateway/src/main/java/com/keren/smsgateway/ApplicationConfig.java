/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway;


import com.keren.smsgateway.jaxrs.impl.SMSCALLRSImpl;
import com.keren.smsgateway.jaxrs.impl.SMSINRSImpl;
import com.keren.smsgateway.jaxrs.impl.SMSOUTRSImpl;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Commercial_2
 */
@javax.ws.rs.ApplicationPath("/")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(SMSCALLRSImpl.class);
        resources.add(SMSINRSImpl.class);
        resources.add(SMSOUTRSImpl.class);        
    }
    
}
