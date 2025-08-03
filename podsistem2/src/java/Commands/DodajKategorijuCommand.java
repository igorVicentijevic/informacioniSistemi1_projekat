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
 * @author igor
 */
public class DodajKategorijuCommand extends Command {

    @Override
    public Return obradiKomandu(Object receivedObject) {
       int[] keys = (int[]) receivedObject;
       String response = DatabaseHandler.getInstance().dodajKategoriju(keys[0],keys[1]);
       Return r = new Return("dodajKategoriju",response);
       return r;           
    }
    
}
