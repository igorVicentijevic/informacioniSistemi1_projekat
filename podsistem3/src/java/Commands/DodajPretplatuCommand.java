/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import SharedLibraryPodsistem3.PretplataDTO;
import java.util.Date;

/**
 *
 * @author root
 */
public class DodajPretplatuCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
//        Object[] objects=(Object[]) receivedObject;
//        int idK = (int) objects[0];
//        int idPak = (int) objects[1];
//        Date datumVremePretplate = (Date) objects[2];

        PretplataDTO pretplataDTO = (PretplataDTO) receivedObject;

        
       
        String response = new DatabaseHandler().dodajPretplatu
        (pretplataDTO.korisnik.idK, pretplataDTO.paket.idPak, pretplataDTO.datumVremePretplate);

        Return r = new Return("dodajPretplatu",response);
        return r;
        
    }
    
}
