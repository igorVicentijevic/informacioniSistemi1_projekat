/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.PaketDTO;
import entities.Paket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author root
 */
public class DohvatiSvePaketeCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        List<Paket> paketi = new DatabaseHandler().dohvatiSvePakete();
        List<PaketDTO> paketiDTO = new ArrayList<>();
        for(Paket p: paketi)
            paketiDTO.add(p.convertToDTO());
        Return r = new Return("dohvatiSvePakete",paketiDTO);
        return r;
    }
    
}
