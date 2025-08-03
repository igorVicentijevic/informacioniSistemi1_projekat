/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import Database.KorisnikAddedListener;
import Database.KorisnikDeletedListener;
import Database.KorisnikUpdatedListener;
import SharedLibrary.KorisnikDTO;
import SyncronizationSharedLibrary.SyncMessage;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 *
 * @author igor
 */
public class DatabaseChangeListener implements KorisnikAddedListener, KorisnikDeletedListener,KorisnikUpdatedListener {


    @Override
    public void OnKorisnikAdded(KorisnikDTO korisnikDTO) {
        Syncronizer sync = new SyncronizerKorisnik(korisnikDTO, SyncMessage.Operation.INSERT);
        sync.start();
    }

    @Override
    public void OnKorisnikDeleted(KorisnikDTO korisnikDTO) {
        Syncronizer sync = new SyncronizerKorisnik(korisnikDTO, SyncMessage.Operation.DELETE);
        sync.start();
    }

    @Override
    public void OnKorisnikUpdated(KorisnikDTO korisnikDTO) {
        Syncronizer sync = new SyncronizerKorisnik(korisnikDTO, SyncMessage.Operation.UPDATE);
        sync.start();
    }
    
}
