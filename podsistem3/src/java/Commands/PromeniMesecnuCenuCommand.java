/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;

/**
 *
 * @author root
 */
public class PromeniMesecnuCenuCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        Object[] objects  = (Object[]) receivedObject;
        int idPak = (int) objects[0];
        float cena = (float) objects[1];
        
        String response = new DatabaseHandler().promeniCenuPaketa(idPak, cena);
        Return r = new Return("promeniMesecnuCenu",response);
        return r;
    }
    
}
