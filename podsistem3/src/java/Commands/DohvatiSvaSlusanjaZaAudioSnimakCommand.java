/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.SlusaDTO;
import entities.Slusa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class DohvatiSvaSlusanjaZaAudioSnimakCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        int idSni = (int) receivedObject;
        List<Slusa> slusanja = new DatabaseHandler().dohvatiSvaSlusanjaZaAudioSnimak(idSni);
        List<SlusaDTO> slusanjaDTO = new ArrayList<>();
        if(slusanja!= null)
            for(Slusa s: slusanja)
                slusanjaDTO.add(s.convertToDTO());
            
        Return r = new Return("dohvatiSvaSlusanjaZaAudioSnimak", slusanjaDTO);
        return r;
    }
    
}
