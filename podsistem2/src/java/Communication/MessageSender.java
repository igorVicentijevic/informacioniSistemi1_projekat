/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import SharedLibrary.Return;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import podsistem2.Main;

/**
 *
 * @author igor
 */
public class MessageSender extends Thread {

    private JMSProducer producer;
    private JMSContext context;
    
    private Return toSend;
    public MessageSender(Return toSend){
        this.toSend = toSend;
    }
    
    private void sendObject(){
   
        try {
            Return r = toSend;
            ObjectMessage om = context.createObjectMessage();
            om.setJMSCorrelationID(r.getCommand());
            om.setObject(r);
            producer.send(Main.toCentralniSistemQ, om);
            
            System.out.println("Return data sent for command "+r.getCommand()+"!");
        } catch (JMSException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    }
    @Override
    public void run() {
        context = Main.connFactory.createContext();
        producer = context.createProducer();
        sendObject();
    }
    
}
