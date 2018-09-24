/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kerem.commons;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

/**
 *Message Driven Bean Builder Helper
 * @author BEKO
 */
public class KerenCoreMDBHelper {
    
    /**
     * 
     * @param message
     * @param connectionFactory
     * @param destination
     * @throws JMSException 
     */
    public static void textMessageProducer(String text,ConnectionFactory connectionFactory,Destination destination) throws JMSException{
        TopicConnection connection =null;
        TopicSession session = null;
        MessageProducer producer = null;
      try{ 
        connection = (TopicConnection) connectionFactory.createConnection();
        session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(text);
        producer.send(message);
        System.out.println(KerenCoreMDBHelper.class+" =========== Message was send to the topic");
      }finally{
         if(producer!=null){producer.close();}
         if(session!=null){session.close();}
         if(connection!=null){connection.close();}
      }
    }
}
