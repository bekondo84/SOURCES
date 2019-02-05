/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.keren.smsgateway.core.ifaces;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author BEKO
 */
@Local
public interface OutBoundServer {
     /**
     * Permet de definir la frequence de traitement des 
     * evenements 
     * @param initialExpiration
     * @param duration
     */
     public void scheduleEventManager(Date initialExpiration , long duration);
}
