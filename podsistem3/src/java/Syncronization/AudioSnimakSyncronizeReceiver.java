/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import Database.DatabaseSyncronizator;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SyncronizationSharedLibrary.SyncMessage;
import javax.jms.JMSContext;
import javax.jms.Topic;
import podsistem3.Main;

/**
 *
 * @author igor
 */
public class AudioSnimakSyncronizeReceiver extends SyncronizeReceiver {

    public AudioSnimakSyncronizeReceiver( JMSContext context) {
        super(Main.audioSnimakTopic, context, "audioSnimak");
    }

    @Override
    protected void handleDatabaseOperation(Object objectToSyncronize, SyncMessage.Operation operation) {
        AudioSnimakDTO audioSnimakDTO = (AudioSnimakDTO) objectToSyncronize;
        switch(operation){
            case INSERT:
                new DatabaseSyncronizator().perzistirajAudioSnimak(audioSnimakDTO);
                break;
            case UPDATE:
                new DatabaseSyncronizator().azurirajAudioSnimak(audioSnimakDTO);
                break;
            case DELETE:
                new DatabaseSyncronizator().izbrisiAudioSnimak(audioSnimakDTO);
                break;
                
        }
              
    }
    
}
