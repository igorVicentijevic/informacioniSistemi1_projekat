/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import Database.DatabaseHandler;
import Database.DatabaseSyncronizator;
import SharedLibrary.KorisnikDTO;
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
public class SyncronizeReceiver extends Thread{
    private Topic syncTopic;
    private JMSContext context;
    
    public SyncronizeReceiver(Topic syncTopic, JMSContext context){
        this.syncTopic = syncTopic;
        this.context = context;      
    }
    private void handleMessageReceived(Message m){
        try {
            ObjectMessage om = (ObjectMessage) m;
            SyncMessage syncMessage = (SyncMessage) om.getObject();
            System.out.println("Received sync message for korisnik with operation: "+syncMessage.operation.toString());
            KorisnikDTO korisnikDTO = (KorisnikDTO) syncMessage.objectToSyncronize;
            
            switch(syncMessage.operation){
                case INSERT:
                    new DatabaseSyncronizator().perzistirajKorisnika(korisnikDTO);
                    break;
                case UPDATE:
                    new DatabaseSyncronizator().azurirajKorisnika(korisnikDTO);
                    break;
                case DELETE:
                    new DatabaseSyncronizator().izbrisiKorisnika(korisnikDTO);
                    break;
                    
            }
        } catch (JMSException ex) {
            Logger.getLogger(SyncronizeReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        JMSConsumer consumer = context.createConsumer(syncTopic,"",true);
        consumer.setMessageListener((Message m)->{
            handleMessageReceived(m);
        });
        while(true);
    }
    
    
    
    
}
