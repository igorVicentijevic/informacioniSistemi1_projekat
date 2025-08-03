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
        map.put("kreirajPaket", new KreirajPaketCommand());
        map.put("dodajPretplatu", new DodajPretplatuCommand());
        map.put("promeniMesecnuCenu", new PromeniMesecnuCenuCommand());
        map.put("dodajSlusanje", new DodajSlusanjeCommand());
        map.put("dodajSnimakUOmiljene", new DodajSnimakUOmiljeneCommand());
        map.put("dodajOcenu", new DodajOcenuCommand());
        map.put("promeniOcenu", new PromeniOcenuCommand());
        map.put("dohvatiSvePakete", new DohvatiSvePaketeCommand());
        map.put("dohvatiSvePretplateZaKorisnika", new DohvatiPretplateZaKorisnikaCommand());
        map.put("dohvatiSvaSlusanjaZaAudioSnimak", new DohvatiSvaSlusanjaZaAudioSnimakCommand());
        map.put("dohvatiSveOceneZaAudioSnimak", new DohvatiSveOceneZaAudioSnimakCommand());
        map.put("dohvatiOmiljeneSnimkeZaKorisnika", new DohvatiOmiljeneSnimkeZaKorisnikaCommand());
    }
    
    public Command getCommand(String commandName){
        return map.get(commandName);
    }
}
