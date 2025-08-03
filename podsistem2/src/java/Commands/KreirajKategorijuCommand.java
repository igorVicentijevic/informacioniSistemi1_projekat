/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibraryPodsistem2.KategorijaDTO;
import SharedLibrary.Return;

/**
 *
 * @author igor
 */
public class KreirajKategorijuCommand extends Command{

    
    @Override
    public Return obradiKomandu(Object receivedObject) {
        KategorijaDTO kategorijaDTO = (KategorijaDTO) receivedObject;
        String response = DatabaseHandler.getInstance().perzistirajKategoriju(kategorijaDTO);
        return new Return("kreirajKategoriju",response);
        
    }

    
    
}
