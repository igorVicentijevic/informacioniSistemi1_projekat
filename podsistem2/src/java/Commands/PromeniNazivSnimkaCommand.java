/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem2.AudioSnimakDTO;

/**
 *
 * @author igor
 */
public class PromeniNazivSnimkaCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        AudioSnimakDTO audioSnimakNewNazivDTO = (AudioSnimakDTO) receivedObject;
        String response = DatabaseHandler.getInstance().promeniNazivSnimka(audioSnimakNewNazivDTO);
        Return r = new Return("promeniNazivSnimka",response);
        return r;
    }
    
}
