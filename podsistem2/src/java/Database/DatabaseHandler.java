/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import SharedLibraryPodsistem2.KategorijaDTO;
import entities.Kategorija;
import entities.Korisnik;
import entities.Snimak;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author igor
 */
public class DatabaseHandler {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem2PU");
    
    private EntityManager em;

    private List<AudioSnimakDeletedListener> audioSnimakDeletedListeners;
    private List<AudioSnimakUpdatedListener> audioSnimakUpdatedListeners;
    private List<AudioSnimakAddedListener> audioSnimakAddedListeners;

    
    private static DatabaseHandler instance=null;
    public static DatabaseHandler getInstance(){
        if(instance == null){
            instance = new DatabaseHandler();
        }
        return instance;
    }
    private DatabaseHandler(){
        em = emf.createEntityManager();
        audioSnimakDeletedListeners = new ArrayList<>();
        audioSnimakUpdatedListeners = new ArrayList<>();
        audioSnimakAddedListeners = new ArrayList<>();
    }
    
    public void addAudioSnimakDeletedListener(AudioSnimakDeletedListener audioSnimakDeletedListener){
        audioSnimakDeletedListeners.add(audioSnimakDeletedListener);
    }
    
    public void addAudioSnimakAddedListener(AudioSnimakAddedListener audioSnimakAddedListener){
        audioSnimakAddedListeners.add(audioSnimakAddedListener);
    }
    
    public void addAudioSnimakUpdatedListener(AudioSnimakUpdatedListener audioSnimakUpdatedListener){
        audioSnimakUpdatedListeners.add(audioSnimakUpdatedListener);
    }
    
    private void notifyAudioSnimakDeleteListeners(AudioSnimakDTO audioSnimakDTO){
        for(AudioSnimakDeletedListener listener: audioSnimakDeletedListeners){
            listener.OnAudioSnimakDeleted(audioSnimakDTO);
        }
    }
    
    private void notifyAudioSnimakUpdatedListeners(AudioSnimakDTO audioSnimakDTO){
        for(AudioSnimakUpdatedListener listener: audioSnimakUpdatedListeners){
            listener.OnAudioSnimakUpdated(audioSnimakDTO);
        }
    }
    
    private void notifyAudioSnimakAddedListeners(AudioSnimakDTO audioSnimakDTO){
        for(AudioSnimakAddedListener listener: audioSnimakAddedListeners){
            listener.OnAudioSnimakAdded(audioSnimakDTO);
        }
    }
    
    public String perzistirajAudioSnimak(AudioSnimakDTO audioSnimakDTO){
        try{
            em.getTransaction().begin();
            
            //TypedQuery<Kategorija> kategorija = em.createNamedQuery("Kategorija.findByNaziv", Kategorija.class);
            
            Snimak snimak = new Snimak();
            
            if(audioSnimakDTO.idSni != null){
                Snimak s = em.find(Snimak.class,audioSnimakDTO.idSni);
                if(s != null){
                    return "Audio snimak sa idSni="+s.getIdSni()+" vec postoji!";
                }
            }
            
            Korisnik k = em.find(Korisnik.class, audioSnimakDTO.vlasnik.idK);
            if(k == null){
                return "Ne postoji korisnik u bazi koji je vlasnik za dati snimak!";
            }
            
            if(audioSnimakDTO.idSni!=null)
                snimak.setIdSni(audioSnimakDTO.idSni);
            
            snimak.setIdKvlasnik(k);
            snimak.setNaziv(audioSnimakDTO.naziv);
            snimak.setTrajanje(audioSnimakDTO.trajanje);
            snimak.setVremePostavljanja(audioSnimakDTO.vremePostavljanja);
            
            em.persist(snimak);
            
            
            em.getTransaction().commit();
            
            notifyAudioSnimakAddedListeners(snimak.convertToDTO());
            return "Audio snimak uspesno dodato u bazu";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }

    }
    public String perzistirajKategoriju(KategorijaDTO kategorijaDTO){
        try{
            em.getTransaction().begin();
            
            //TypedQuery<Kategorija> kategorija = em.createNamedQuery("Kategorija.findByNaziv", Kategorija.class);
            
            Kategorija kategorija = new Kategorija();
            kategorija.setNaziv(kategorijaDTO.naziv);
            
            em.persist(kategorija);

            
            em.getTransaction().commit();
            return "Kategorija uspesno dodato u bazu";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }

    }
    
    public List<Kategorija> dohvatiKategorijeZaAudioSnimak(AudioSnimakDTO audioSnimakDTO){
        try{
            em.getTransaction().begin();
            
            Snimak snimak = em.find(Snimak.class, audioSnimakDTO.idSni);
            if(snimak == null)
                return null;
            
            
            
            List<Kategorija> kategorije = snimak.getKategorijaList();
            em.getTransaction().commit();
            
            System.out.println("Uspesno dohvacene sve kategorije za snimak");
            return kategorije;
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return null;
        }

    }

    public String promeniNazivSnimka(AudioSnimakDTO audioSnimakNewNazivDTO) {
        try{
            em.getTransaction().begin();
            
            //TypedQuery<Kategorija> kategorija = em.createNamedQuery("Kategorija.findByNaziv", Kategorija.class);
            Snimak snimak = em.find(Snimak.class,audioSnimakNewNazivDTO.idSni);
            if(snimak == null)
                return "Ne postoji audio snimak u bazi!";
            
            snimak.setNaziv(audioSnimakNewNazivDTO.naziv);
            
            em.getTransaction().commit();
            
            notifyAudioSnimakUpdatedListeners(snimak.convertToDTO());
            return "Naziv snimka uspesno promenjen";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }

    }

    public String dodajKategoriju(int idSni, int idKat) {
       try{
            em.getTransaction().begin();
            Snimak snimak = em.find(Snimak.class,idSni);
            if(snimak == null)
                return "Ne postoji snimak u bazi!";
            Kategorija kategorija = em.find(Kategorija.class, idKat);
            if(kategorija == null)
                return "Ne postoji kategorija u bazi!";
            
            snimak.getKategorijaList().add(kategorija);
            kategorija.getSnimakList().add(snimak);
            em.getTransaction().commit();
            return "Kategorija uspesno dodeljena snimku";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
   
    }

    public String izbrisiSnimakOdStraneKorisnika(int idK, int idSni) {
        try{
            em.getTransaction().begin();
            Snimak snimak = em.find(Snimak.class,idSni);
            if(snimak == null)
                return "Ne postoji snimak u bazi!";
            Korisnik korisnik = em.find(Korisnik.class, idK);
            if(korisnik== null)
                return "Ne postoji korisnik u bazi!";
            
            if(korisnik.getIdK() != snimak.getIdKvlasnik().getIdK())
                return "Korisnik nije vlasnik snimka!";
            
            em.remove(snimak);
            em.getTransaction().commit();
            notifyAudioSnimakDeleteListeners(snimak.convertToDTO());
            return "Snimak uspesno obrisan!";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
      
    }

    public List<Kategorija> dohvatiSveKategorije() {
        try{
            em.getTransaction().begin();
            
            String query = "SELECT k FROM Kategorija k";
            TypedQuery<Kategorija> kategorijaTQ = em.createQuery(query, Kategorija.class);
            List<Kategorija> sveKategorije = kategorijaTQ.getResultList();
            
            em.getTransaction().commit();
            
            System.out.println("Uspesno dohvacene sve kategorije");
            return sveKategorije;
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return null;
        }
   
    }
    
    public List<Snimak> dohvatiSveSnimke() {
        try{
            em.getTransaction().begin();
            
            String query = "SELECT s FROM Snimak s";
            TypedQuery<Snimak> snimciTQ = em.createQuery(query, Snimak.class);
            List<Snimak> sviSnimci = snimciTQ.getResultList();
            
            em.getTransaction().commit();
            
            System.out.println("Uspesno dohvacene sve kategorije");
            return sviSnimci;
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return null;
        }
     
    }
    
}