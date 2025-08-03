/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import Syncronization.Syncronizer;
import Syncronization.SyncronizerAudioSnimak;
import SyncronizationSharedLibrary.SyncMessage;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 *
 * @author igor
 */
public class DatabaseChangeListener implements AudioSnimakAddedListener, AudioSnimakDeletedListener,AudioSnimakUpdatedListener {


    @Override
    public void OnAudioSnimakAdded(AudioSnimakDTO audioSnimakDTO) {
        Syncronizer sync = new SyncronizerAudioSnimak(audioSnimakDTO, SyncMessage.Operation.INSERT);
        sync.start();
    }

    @Override
    public void OnAudioSnimakDeleted(AudioSnimakDTO audioSnimakDTO) {
        Syncronizer sync = new SyncronizerAudioSnimak(audioSnimakDTO, SyncMessage.Operation.DELETE);
        sync.start();
    }

    @Override
    public void OnAudioSnimakUpdated(AudioSnimakDTO audioSnimakDTO) {
        Syncronizer sync = new SyncronizerAudioSnimak(audioSnimakDTO, SyncMessage.Operation.UPDATE);
        sync.start();
    }
    
}
