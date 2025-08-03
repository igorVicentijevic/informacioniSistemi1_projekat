/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import Database.DatabaseHandler;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import SharedLibrary.Return;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 *
 * @author igor
 */
public class KreirajGradCommand implements Command {
    private void posalji(String returnInfo){
        System.out.println("sending return data for kreirajGrad!");
        Return r = new Return("kreirajGrad", returnInfo);
        new MessageSender(r).start();
    }

    private void kreirajGrad(MestoDTO mestoDTO){
        String returnInfo = DatabaseHandler.getInstance().perzistirajMesto(mestoDTO);
        posalji(returnInfo);
    
    }
    @Override
    public void execute(Object o) {
        if( !(o instanceof MestoDTO)) return;
        
        kreirajGrad((MestoDTO) o);
        
    }
    
}
