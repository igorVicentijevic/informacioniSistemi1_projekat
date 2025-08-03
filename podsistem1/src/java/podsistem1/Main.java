/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import Database.DatabaseHandler;
import Syncronization.DatabaseChangeListener;
import Syncronization.SyncronizeReceiver;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 *
 * @author igor
 */
public class Main {

    @Resource(lookup="jms/__defaultConnectionFactory")
    public static  ConnectionFactory connFactory;
    
    @Resource(lookup="podsistem1Q")
     public static  javax.jms.Queue podsistem1Q;
    
    @Resource(lookup="podsistem1CentralniSistemQ")
    public  static  javax.jms.Queue toCentralniSistemQ;
    
    @Resource(lookup="korisnikTopic")
    public static Topic korisnikTopic;
    
    
    public static void main(String[] args) {
        System.out.println("podsistem1 has started...");
        
        DatabaseChangeListener databaseChangeListener = new DatabaseChangeListener();
        DatabaseHandler.getInstance().addKorisnikAddedListener(databaseChangeListener);
        DatabaseHandler.getInstance().addKorisnikDeletedListener(databaseChangeListener);
        DatabaseHandler.getInstance().addKorisnikUpdatedListener(databaseChangeListener);


        MessageReceiver msgReceiver = new MessageReceiver();
        //msgReceiver.setFlush(true);
        msgReceiver.start();
        
        SyncronizeReceiver syncReceiver = new SyncronizeReceiver(korisnikTopic,connFactory.createContext());
        syncReceiver.start();
    }
    
}
