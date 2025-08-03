/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entiteti.Korisnik;
import Entiteti.Mesto;
import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import Syncronization.Syncronizer;
import Syncronization.SyncronizerKorisnik;
import SyncronizationSharedLibrary.SyncMessage;
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
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem1PU");
    
    private EntityManager em;
    private List<KorisnikDeletedListener> korisnikDeletedListeners;
    private List<KorisnikUpdatedListener> korisnikUpdatedListeners;
    private List<KorisnikAddedListener> korisnikAddedListeners;

    
    private static DatabaseHandler instance=null;
    public static DatabaseHandler getInstance(){
        if(instance == null){
            instance = new DatabaseHandler();
        }
        return instance;
    }
    private DatabaseHandler(){
        em = emf.createEntityManager();
        korisnikDeletedListeners = new ArrayList<>();
        korisnikUpdatedListeners = new ArrayList<>();
        korisnikAddedListeners = new ArrayList<>();
    }
    
    public void addKorisnikDeletedListener(KorisnikDeletedListener korisnikDeletedListener){
        korisnikDeletedListeners.add(korisnikDeletedListener);
    }
    
    public void addKorisnikAddedListener(KorisnikAddedListener korisnikAddedListener){
        korisnikAddedListeners.add(korisnikAddedListener);
    }
    
    public void addKorisnikUpdatedListener(KorisnikUpdatedListener korisnikUpdatedListener){
        korisnikUpdatedListeners.add(korisnikUpdatedListener);
    }
    
    private void notifyKorisnikDeleteListeners(KorisnikDTO korisnikDTO){
        for(KorisnikDeletedListener listener: korisnikDeletedListeners){
            listener.OnKorisnikDeleted(korisnikDTO);
        }
    }
    
    private void notifyKorisnikUpdatedListeners(KorisnikDTO korisnikDTO){
        for(KorisnikUpdatedListener listener: korisnikUpdatedListeners){
            listener.OnKorisnikUpdated(korisnikDTO);
        }
    }
    
    private void notifyKorisnikAddedListeners(KorisnikDTO korisnikDTO){
        for(KorisnikAddedListener listener: korisnikAddedListeners){
            listener.OnKorisnikAdded(korisnikDTO);
        }
    }
    public String perzistirajMesto(MestoDTO mestoDTO){
        try{
            em.getTransaction().begin();
            
            Mesto mesto = new Mesto();
            mesto.setNaziv(mestoDTO.naziv);

            em.persist(mesto);
            em.getTransaction().commit();
            
            return "Mesto uspesno dodato u bazu";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
       
        
    }
    
    public List<Korisnik> dohvatiSveKorisnike(){
        try{
            em.getTransaction().begin();
            
            String query = "SELECT k FROM Korisnik k";
            TypedQuery<Korisnik> mestoTQ = em.createQuery(query, Korisnik.class);
            List<Korisnik> sviKorisnici = mestoTQ.getResultList();
            
            em.getTransaction().commit();
            
            System.out.println("Uspesno dohvaceni svi korisnici");
            return sviKorisnici;
        }
        catch(Exception e){
               System.out.println(e.getMessage());
        }
       
        return null;
    }
    
    public List<Mesto> dohvatiSvaMesta(){
        try{
            em.getTransaction().begin();
            
            String query = "SELECT m FROM Mesto m";
            TypedQuery<Mesto> mestoTQ = em.createQuery(query, Mesto.class);
            List<Mesto> svaMesta = mestoTQ.getResultList();
            
            em.getTransaction().commit();
            
            System.out.println("Uspesno dohvacena sva mesta");
            return svaMesta;
        }
        catch(Exception e){
               System.out.println(e.getMessage());
        }
     
        return null;
    }
    
    public String promeniEmail(KorisnikDTO korisnikNewEmail){
        try{
            em.getTransaction().begin();
            
            if(korisnikNewEmail.idK == null)
                return "Nije postavljen primarni kljuc za korisnika!";
            
            Korisnik korisnik = em.find(Korisnik.class, korisnikNewEmail.idK);
            
            if(korisnik == null){
                return "Korisnik sa idK " + korisnikNewEmail.idK +" ne postoji u bazi!";
            }
            
            korisnik.setEmail(korisnikNewEmail.email);
       
            em.getTransaction().commit();
            
//            Syncronizer sync = new SyncronizerKorisnik(korisnik.convertToDTO(),SyncMessage.Operation.UPDATE);
//            sync.start();
            notifyKorisnikUpdatedListeners(korisnik.convertToDTO());
            return "Uspesno izvrsena promena email za korisnika idK:"+korisnik.getIdK();
        }
        catch(Exception e){
           
              System.out.println(e.getMessage());
              return "Doslo je do greske!";
        }
   
        
    }
    
    public String promeniMestoZaKorisnika(KorisnikDTO korisnikNewMesto){
        try{
            em.getTransaction().begin();
            if(korisnikNewMesto.idK == null)
                return "Niste poslali primarni kljuc za korisnika!";
            
            Korisnik korisnik = em.find(Korisnik.class, korisnikNewMesto.idK);
            
            if(korisnik == null){
               return "Korisnik sa idK " + korisnikNewMesto.idK +" ne postoji u bazi!";
            }
            
            Mesto mesto = em.find(Mesto.class, korisnikNewMesto.mesto.naziv);
            
            if(mesto==null)
            {
                mesto =  new Mesto();
                mesto.setNaziv(korisnikNewMesto.mesto.naziv);
            }

            korisnik.setMesto(mesto);
       
            em.getTransaction().commit();
//            Syncronizer sync = new SyncronizerKorisnik(korisnik.convertToDTO(),SyncMessage.Operation.UPDATE);
//            sync.start();
            notifyKorisnikUpdatedListeners(korisnik.convertToDTO());
            return "Uspesno izvrsena promena mesta za korisnika idK:"+korisnikNewMesto.idK;
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
 
        
    }
    
    
    public String perzistirajKorisnika(KorisnikDTO korisnikDTO) {
        
        try{
            em.getTransaction().begin();

            Korisnik korisnik = new Korisnik();
            korisnik.setIdK(korisnikDTO.idK);
            korisnik.setIme(korisnikDTO.ime);
            korisnik.setGodiste(korisnikDTO.godiste);
            korisnik.setEmail(korisnikDTO.email);
            korisnik.setPol(korisnikDTO.pol);
            
//            Korisnik k = em.find(Korisnik.class, korisnik.getIdK());
//            if(k != null)
//                return "Korisnik sa idK="+k.getIdK()+" vec postoji!";

            Mesto mesto = em.find(Mesto.class,korisnikDTO.mesto.naziv);


            if(mesto == null)
            {
                mesto = new Mesto();
                mesto.setNaziv(korisnikDTO.mesto.naziv);
            }

            korisnik.setMesto(mesto);

            em.persist(korisnik);
            em.getTransaction().commit();
            
            notifyKorisnikAddedListeners(korisnik.convertToDTO());
            
            return "Korisnik uspesno unet u bazu!";
            
        }
        catch (RollbackException | IllegalStateException e)
        {
            System.out.println(e.getMessage());
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            return "Korisnik nije unet zbog greske!";
        }

        
    }
}
