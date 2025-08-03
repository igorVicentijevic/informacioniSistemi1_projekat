/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
public class KreirajSnimakCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        AudioSnimakDTO snimakDTO = (AudioSnimakDTO) receivedObject;      
        String response = DatabaseHandler.getInstance().perzistirajAudioSnimak(snimakDTO);
        Return r = new Return("kreirajSnimak",response);
        return r;
        
    }
    
}
