/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;

import Communication.MessageReceiver;
import Syncronization.AudioSnimakSyncronizeReceiver;
import Syncronization.KorisnikSyncronizeReceiver;
import Syncronization.SyncronizeReceiver;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 *
 * @author igor
 */
public class Main {

    @Resource(lookup="jms/__defaultConnectionFactory")
    public static ConnectionFactory connFactory;
    
    @Resource(lookup="podsistem3Q")
    public static Queue podsistem3Q;
    
    @Resource(lookup="podsistem3CentralniSistemQ")
    public static Queue toCentralniSistemQ;
    
    @Resource(lookup="korisnikTopic")
    public static Topic korisnikTopic;
    
    @Resource(lookup="audioSnimakTopic")
    public static Topic audioSnimakTopic;
    
    public static void main(String[] args) {
        
        SyncronizeReceiver syncReceiverKorisnik = new KorisnikSyncronizeReceiver(connFactory.createContext());
        SyncronizeReceiver syncReceiverAudioSnimak = new AudioSnimakSyncronizeReceiver(connFactory.createContext());
        syncReceiverAudioSnimak.start();
        syncReceiverKorisnik.start();
        
        MessageReceiver msgReceiver = new MessageReceiver();
        msgReceiver.start();
        
        System.out.println("Podsistem 3 has started...");
        
        
    }
    
}
