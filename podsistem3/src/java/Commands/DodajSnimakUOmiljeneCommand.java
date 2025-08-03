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
public class DodajSnimakUOmiljeneCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
        int[] keys = (int[]) receivedObject;
        
        String response = new DatabaseHandler().dodajSnimakUOmiljene(keys[0], keys[1]);
        
        Return r = new Return("dodajSnimakUOmiljene",response);
        return r;
    }
    
}
