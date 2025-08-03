/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import SyncronizationSharedLibrary.SyncMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

/**
 *
 * @author igor
 */
public abstract class Syncronizer extends Thread {
    
    private Topic syncTopic;
    private JMSContext context;
    private Object objectToSyncronize;
    private SyncMessage.Operation operation;
    private String syncName;
    public Syncronizer(Topic syncTopic,JMSContext context, Object objectToSyncronize,SyncMessage.Operation operation, String syncName){
        this.syncTopic = syncTopic;
        this.context = context;
        this.objectToSyncronize = objectToSyncronize;
        this.operation = operation;
        this.syncName = syncName;
    }   

    @Override
    public void run() {
        syncronize();
    }
    
    
    
    public void syncronize(){
        try {
            JMSProducer producer = this.context.createProducer();
            SyncMessage syncMessage = new SyncMessage(objectToSyncronize, operation);
            ObjectMessage objMessage = context.createObjectMessage();
            objMessage.setObject(syncMessage);
            objMessage.setStringProperty("source","podsistem2");
            producer.send(syncTopic, objMessage);
            
            System.out.println("Syncronization message sent "+syncName+" for with operation: "+operation.toString());
        } catch (JMSException ex) {
            Logger.getLogger(Syncronizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
