/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import Database.DatabaseSyncronizator;
import SharedLibrary.KorisnikDTO;
import SyncronizationSharedLibrary.SyncMessage;
import javax.jms.JMSContext;
import javax.jms.Topic;
import podsistem3.Main;

/**
 *
 * @author igor
 */
public class KorisnikSyncronizeReceiver extends SyncronizeReceiver {

    public KorisnikSyncronizeReceiver( JMSContext context) {
        super(Main.korisnikTopic, context,"korisnik");
    }

    @Override
    protected void handleDatabaseOperation(Object objectToSyncronize, SyncMessage.Operation operation) {
            KorisnikDTO korisnikDTO = (KorisnikDTO) objectToSyncronize;
            
            switch(operation){
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
    }
    
}
