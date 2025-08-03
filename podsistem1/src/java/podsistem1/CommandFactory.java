/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author igor
 */
public class CommandFactory {
    private Map<String, Command> map= new HashMap<>();
    
    public CommandFactory(){
        map.put("kreirajKorisnika", new KreirajKorisnikaCommand());
        map.put("kreirajGrad", new KreirajGradCommand());
        map.put("promeniEmail", new PromeniEmailCommand());
        map.put("promeniMesto", new PromeniMestoZaKorisnikaCommand());
        map.put("dohvatiSvaMesta", new dohvatiSvaMestaCommand());
        map.put("dohvatiSveKorisnike", new DohvatiSveKorisnikeCommand());
    }
    
    public Command getCommand(String commandName){
        return map.get(commandName);
    }
}
