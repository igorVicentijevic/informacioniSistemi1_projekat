/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2;

import Communication.MessageReceiver;
import Database.DatabaseChangeListener;
import Database.DatabaseHandler;
import Syncronization.SyncronizeReceiver;
import Syncronization.Syncronizer;
import Syncronization.SyncronizerKorisnik;
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
    
    @Resource(lookup="podsistem2Q")
     public static  javax.jms.Queue podsistem2Q;
    
    @Resource(lookup="podsistem2CentralniSistemQ")
     public static  javax.jms.Queue toCentralniSistemQ;
    
    //Syncronization
    
    @Resource(lookup="korisnikTopic")
    public static Topic korisnikTopic;
    
    @Resource(lookup="audioSnimakTopic")
    public static Topic audioSnimakTopic;
    
    public static void main(String[] args) {
        
        new MessageReceiver().start();
        SyncronizeReceiver syncReceiver = new SyncronizeReceiver(korisnikTopic,connFactory.createContext());
        syncReceiver.start();
        
        DatabaseChangeListener databaseChangeListener = new DatabaseChangeListener();
        DatabaseHandler.getInstance().addAudioSnimakAddedListener(databaseChangeListener);
        DatabaseHandler.getInstance().addAudioSnimakUpdatedListener(databaseChangeListener);
        DatabaseHandler.getInstance().addAudioSnimakDeletedListener(databaseChangeListener);
        
        System.out.println("podsistem2 has started");
        
        
    }

    
}
