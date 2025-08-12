/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem2.KategorijaDTO;
import entities.Kategorija;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class DohvatiSveKategorijeCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        
       
        List<Kategorija> sveKategorije = DatabaseHandler.getInstance().dohvatiSveKategorije();
        List<KategorijaDTO> sveKategorijeDTO = new ArrayList<>();
        if(sveKategorije != null)
            for(Kategorija k: sveKategorije){
                sveKategorijeDTO.add(k.convertToDTO());
            }
        Return r = new Return("dohvatiSveKategorije",sveKategorijeDTO);
        return r;
        
    }
    
}
