/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import Database.DatabaseHandler;
import Entiteti.Korisnik;
import Entiteti.Mesto;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import SharedLibrary.Return;
import java.util.ArrayList;
import java.util.List;
import javax.jms.ObjectMessage;

/**
 *
 * @author igor
 */
public class DohvatiSveKorisnikeCommand implements Command {

   private KorisnikDTO konvertujUKorisnikDTO(Korisnik korisnik){
        KorisnikDTO korisnikDTO = new KorisnikDTO();
        korisnikDTO.idK = korisnik.getIdK();
        korisnikDTO.email =korisnik.getEmail();
        korisnikDTO.godiste = korisnik.getGodiste();
        korisnikDTO.ime = korisnik.getIme();
        korisnikDTO.pol = korisnik.getPol();
        
        korisnikDTO.mesto = korisnik.getMesto().convertToDTO();
        
        return korisnikDTO;
    }
    
    private void posalji(List<KorisnikDTO> korisniciDTO){
        System.out.println("Dohvaceni korisnici se salju...");
        Return r = new Return("dohvatiSveKorisnike", korisniciDTO);
        new MessageSender(r).start();
    }
    private void dohvatiSveKorisnike(){
        List<Korisnik> sviKorisnici = DatabaseHandler.getInstance().dohvatiSveKorisnike();
        List<KorisnikDTO> korisniciDTO = new ArrayList<>();
        for(Korisnik k: sviKorisnici){
           korisniciDTO.add(konvertujUKorisnikDTO(k));
        }
        
        posalji(korisniciDTO);
        
    }
    
    @Override
    public void execute(Object o) {

        dohvatiSveKorisnike();
    }
    
    
    
}
