/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Communication.MessageSender;
import SharedLibrary.Return;

/**
 *
 * @author igor
 */
public abstract  class Command {
    
    private void posalji(Return r){
        System.out.println("sending return data for "+r.getCommand());   
        new MessageSender(r).start();
    }
    
    //public abstract boolean checkIfGivenObjectIsValidType(Object o);
    public abstract Return obradiKomandu(Object receivedObject);
    
    public void execute(Object o){
        Return r = obradiKomandu(o);
        posalji(r);

    }

}
