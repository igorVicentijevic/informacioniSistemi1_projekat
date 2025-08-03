/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import entities.Snimak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class DohvatiOmiljeneSnimkeZaKorisnikaCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        int idK = (int) receivedObject;
        List<Snimak> omiljeniSnimci = new DatabaseHandler().dohvatiOmiljeneSnimkeZaKorisnika(idK);
        List<AudioSnimakDTO> omiljeniSnimciDTO = new ArrayList<>();
        for(Snimak s: omiljeniSnimci)
            omiljeniSnimciDTO.add(s.convertToDTO());
        Return r  = new Return("dohvatiOmiljeneSnimkeZaKorisnika",omiljeniSnimciDTO);
        return r;
    }
    
}
