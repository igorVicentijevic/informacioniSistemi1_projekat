/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import entities.Kategorija;
import entities.Snimak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class DohvatiSveSnimkeCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
       List<Snimak> sviSnimci = DatabaseHandler.getInstance().dohvatiSveSnimke();
        List<AudioSnimakDTO> sviSnimciDTO = new ArrayList<>();
        for(Snimak s: sviSnimci){
            sviSnimciDTO.add(s.convertToDTO());
        }
        Return r = new Return("dohvatiSveSnimke",sviSnimciDTO);
        return r;
    }
    
}
