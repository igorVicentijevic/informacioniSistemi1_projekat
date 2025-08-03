/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import Database.DatabaseHandler;
import Entiteti.Mesto;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import SharedLibrary.Return;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 *
 * @author igor
 */
public class dohvatiSvaMestaCommand implements Command {

    private MestoDTO konvertujUMestoDTO(Mesto mesto){
        MestoDTO mestoDTO = new MestoDTO();
        mestoDTO.naziv = mesto.getNaziv();
        return mestoDTO;
    }
    
    private void posalji(List<MestoDTO> mesta){
        System.out.println("Poslati podaci za dohvatiSvaMesta");
        Return r = new Return("dohvatiSvaMesta", mesta);
        new MessageSender(r).start();
    }
    private void dohvatiSvaMesta(){
        List<Mesto> svaMesta = DatabaseHandler.getInstance().dohvatiSvaMesta();
        List<MestoDTO> mestaDTO = new ArrayList<>();
        for(Mesto m: svaMesta){
           mestaDTO.add(konvertujUMestoDTO(m));
        }
        
        posalji(mestaDTO);
        
    }
    
    @Override
    public void execute(Object o) {
        if( !(o instanceof MestoDTO)) return;
        dohvatiSvaMesta();
    }
    
}
