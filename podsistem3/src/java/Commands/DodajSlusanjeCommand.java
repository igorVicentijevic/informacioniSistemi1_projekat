/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.SlusaDTO;


/**
 *
 * @author root
 */
public class DodajSlusanjeCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        SlusaDTO slusaDTO = (SlusaDTO) receivedObject;
        String response = new DatabaseHandler().dodajSlusanje(slusaDTO);
        Return r = new Return("dodajSlusanje",response);
        return r;
    }
    
}
