/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import Database.DatabaseHandler;
import Database.DatabaseSyncronizator;
import SyncronizationSharedLibrary.SyncMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

/**
 *
 * @author igor
 */
public abstract class SyncronizeReceiver extends Thread{
    private Topic syncTopic;
    private JMSContext context;
    private String syncName;
    
    public SyncronizeReceiver(Topic syncTopic, JMSContext context){
        this.syncTopic = syncTopic;
        this.context = context;      
    }
    
    public SyncronizeReceiver(Topic syncTopic, JMSContext context, String syncName){
        this.syncTopic = syncTopic;
        this.context = context;      
        this.syncName = syncName;
    }
    
    protected abstract void handleDatabaseOperation(Object objectToSyncronize, SyncMessage.Operation operation);
    
    private void handleMessageReceived(Message m){
        try {
            ObjectMessage om = (ObjectMessage) m;
            SyncMessage syncMessage = (SyncMessage) om.getObject();
            System.out.println("Received sync message for "+syncName+" with operation: "+syncMessage.operation.toString());
            handleDatabaseOperation(syncMessage.objectToSyncronize, syncMessage.operation);
        } catch (JMSException ex) {
            Logger.getLogger(SyncronizeReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        JMSConsumer consumer = context.createConsumer(syncTopic,"", true);
        while(true){
            Message msg = consumer.receive();
            handleMessageReceived(msg);
        }
    }
    
    
    
    
}
