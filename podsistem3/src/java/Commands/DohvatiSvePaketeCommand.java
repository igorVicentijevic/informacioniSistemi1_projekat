/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Database.DatabaseHandler;
import SharedLibrary.Return;
import entities.Paket;
import java.util.List;

/**
 *
 * @author root
 */
public class DohvatiSvePaketeCommand extends Command{

    @Override
    public Return obradiKomandu(Object receivedObject) {
        List<Paket> paketi = new DatabaseHandler().dohvatiSvePakete();
        Return r = new Return("dohvatiSvePakete",paketi);
        return r;
    }
    
}
