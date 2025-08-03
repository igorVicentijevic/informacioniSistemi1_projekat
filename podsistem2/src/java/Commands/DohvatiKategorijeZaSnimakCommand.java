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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igor
 */
public class DohvatiKategorijeZaSnimakCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        AudioSnimakDTO audioSnimakDTO = (AudioSnimakDTO) receivedObject;
        
        List<Kategorija> kategorije = DatabaseHandler.getInstance().dohvatiKategorijeZaAudioSnimak(audioSnimakDTO);
        List<KategorijaDTO> kategorijeDTO = new ArrayList<>();
        
        for(Kategorija k: kategorije){
            kategorijeDTO.add(k.convertToDTO());
        }
        
        Return r = new Return("dohvatiKategorijeZaAudioSnimak",kategorijeDTO);
        return r;
    }
    
}
