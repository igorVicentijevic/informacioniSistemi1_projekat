/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Syncronization;

import SharedLibrary.KorisnikDTO;
import SyncronizationSharedLibrary.SyncMessage;
import podsistem3.Main;

/**
 *
 * @author igor
 */
public class SyncronizerKorisnik extends Syncronizer{
    
    public SyncronizerKorisnik(KorisnikDTO korisnikToSyncronize, SyncMessage.Operation operation){
        super(Main.korisnikTopic,Main.connFactory.createContext(),korisnikToSyncronize, operation,"korisnik");
    }
}
