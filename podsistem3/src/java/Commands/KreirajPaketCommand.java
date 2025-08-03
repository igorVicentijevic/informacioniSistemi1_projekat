/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.PaketDTO;
import javax.ws.rs.core.Response;

/**
 *
 * @author igor
 */
public class KreirajPaketCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        PaketDTO paketDTO = (PaketDTO) receivedObject;
        String response = new DatabaseHandler().perzistirajPaket(paketDTO);
        Return r = new Return("kreirajPaket",response);
        return r;
    }
    
}
