/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author igor
 */
public class CommandFactory {
    private Map<String, Command> map= new HashMap<>();
    
    public CommandFactory(){
        map.put("kreirajKategoriju", new KreirajKategorijuCommand());
        map.put("kreirajSnimak",new KreirajSnimakCommand());
        map.put("promeniNazivSnimka",new PromeniNazivSnimkaCommand());
        map.put("dodajKategoriju", new DodajKategorijuCommand());
        map.put("izbrisiSnimak", new IzbrisiSnimakCommand());
        map.put("dohvatiSveKategorije", new DohvatiSveKategorijeCommand());
        map.put("dohvatiSveSnimke",new DohvatiSveSnimkeCommand());
        map.put("dohvatiKategorijeZaAudioSnimak", new DohvatiKategorijeZaSnimakCommand());
        
    }
    
    public Command getCommand(String commandName){
        return map.get(commandName);
    }
}
