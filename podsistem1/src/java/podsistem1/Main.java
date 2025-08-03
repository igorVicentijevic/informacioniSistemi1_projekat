/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import Database.DatabaseHandler;
import Entiteti.Korisnik;
import Entiteti.Mesto;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import Syncronization.DatabaseChangeListener;
import Syncronization.SyncronizeReceiver;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

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
