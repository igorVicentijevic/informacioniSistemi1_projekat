/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.OcenaDTO;

/**
 *
 * @author igor
 */
public class IzbrisiOcenuCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        OcenaDTO ocenaDTO = (OcenaDTO) receivedObject;
        String response = new DatabaseHandler().izbrisiOcenu(ocenaDTO);
        Return r = new Return("izbrisiOcenu",ocenaDTO);
        return r;
    }
    
}
