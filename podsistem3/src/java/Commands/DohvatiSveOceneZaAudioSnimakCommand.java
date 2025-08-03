/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.OcenaDTO;
import entities.Ocena;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class DohvatiSveOceneZaAudioSnimakCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        int idSni = (int) receivedObject;
        List<Ocena> ocene = new DatabaseHandler().dohvatiSveOceneZaAudioSnimak(idSni);
        List<OcenaDTO> oceneDTO = new ArrayList<>();
        for(Ocena o: ocene)
            oceneDTO.add(o.convertToDTO());
        Return r = new Return("dohvatiSveOceneZaAudioSnimak", oceneDTO);
        return r;
            
    }
    
}
