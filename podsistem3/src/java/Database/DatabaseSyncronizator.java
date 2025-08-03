/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import SharedLibrary.KorisnikDTO;
import SharedLibraryPodsistem2.AudioSnimakDTO;
import entities.Korisnik;
import entities.Snimak;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author igor
 */
public class DatabaseSyncronizator{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem3PU");
    
    private EntityManager em;
    public DatabaseSyncronizator(){
        em = emf.createEntityManager();
    }
    public String perzistirajKorisnika(KorisnikDTO korisnikDTO) {
        
        try{
            em.getTransaction().begin();
            
            Korisnik korisnik = em.find(Korisnik.class,korisnikDTO.idK);
            if(korisnik != null)
                return "Korisnik vec postoji";

            korisnik = new Korisnik();
            korisnik.setIdK(korisnikDTO.idK);
            korisnik.setIme(korisnikDTO.ime);
            korisnik.setGodiste(korisnikDTO.godiste);
            korisnik.setEmail(korisnikDTO.email);
            korisnik.setPol(korisnikDTO.pol);

            
            korisnik.setMesto(korisnikDTO.mesto.naziv);
            

            em.persist(korisnik);
            em.getTransaction().commit();
            return "Korisnik uspesno unet u bazu!";
            
        }
        catch (RollbackException | IllegalStateException e)
        {
            System.out.println(e.getMessage());
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            return "Korisnik nije unet zbog greske!";
        }
        finally{
            em.close();
        }
        
        
    }
    public String azurirajKorisnika(KorisnikDTO korisnikDTO) {
        
        try{
            em.getTransaction().begin();

            Korisnik korisnik = em.find(Korisnik.class,korisnikDTO.idK);
            if(korisnik == null)
                return "Ne postoji korisnik";
            
            korisnik.setIme(korisnikDTO.ime);
            korisnik.setGodiste(korisnikDTO.godiste);
            korisnik.setEmail(korisnikDTO.email);
            korisnik.setPol(korisnikDTO.pol);

            
            korisnik.setMesto(korisnikDTO.mesto.naziv);
            

        
            em.getTransaction().commit();
            return "Korisnik uspesno azuriran!";
            
        }
        catch (RollbackException | IllegalStateException e)
        {
            System.out.println(e.getMessage());
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            return "Korisnik nije azuriran zbog greske!";
        }
        finally{
            em.close();
        }
        
        
    }
    
    public String izbrisiKorisnika(KorisnikDTO korisnikDTO) {
        
        try{
            em.getTransaction().begin();

            Korisnik korisnik = em.find(Korisnik.class,korisnikDTO.idK);
            if(korisnik == null)
                return "Ne postoji korisnik";
            
            em.remove(korisnik);

            em.getTransaction().commit();
            return "Korisnik uspesno obrisan!";
            
        }
        catch (RollbackException | IllegalStateException e)
        {
            System.out.println(e.getMessage());
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            return "Korisnik nije obrisan zbog greske!";
        }
        finally{
            em.close();
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
            return "Audio snimak uspesno dodato u bazu";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }
    
    public String azurirajAudioSnimak(AudioSnimakDTO audioSnimakDTO){
        try{
            em.getTransaction().begin();
            
            //TypedQuery<Kategorija> kategorija = em.createNamedQuery("Kategorija.findByNaziv", Kategorija.class);
            
            
            if(audioSnimakDTO.idSni == null)
                return "PK nije postavljen!";
            
            
            Snimak snimak = em.find(Snimak.class,audioSnimakDTO.idSni);
            if(snimak == null){
                return "Audio snimak sa idSni="+snimak.getIdSni()+"ne postoji u bazi!";
            }
            
            
            Korisnik k = em.find(Korisnik.class, audioSnimakDTO.vlasnik.idK);
            if(k == null){
                return "Ne postoji korisnik u bazi koji je vlasnik za dati snimak!";
            }
            
      
            
            snimak.setIdKvlasnik(k);
            snimak.setNaziv(audioSnimakDTO.naziv);
            snimak.setTrajanje(audioSnimakDTO.trajanje);
            snimak.setVremePostavljanja(audioSnimakDTO.vremePostavljanja);
            
           

            
            em.getTransaction().commit();
            return "Audio snimak uspesno azuriran!";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }
    
    public String izbrisiAudioSnimak(AudioSnimakDTO audioSnimakDTO){
        try{
            em.getTransaction().begin();
            
            //TypedQuery<Kategorija> kategorija = em.createNamedQuery("Kategorija.findByNaziv", Kategorija.class);
            
            
            if(audioSnimakDTO.idSni == null)
                return "PK nije postavljen!";
            
            
            Snimak snimak = em.find(Snimak.class,audioSnimakDTO.idSni);
            if(snimak == null){
                return "Audio snimak sa idSni="+snimak.getIdSni()+"ne postoji u bazi!";
            }
            
           em.remove(snimak);
            
           

            
            em.getTransaction().commit();
            return "Audio snimak uspesno izbrisan!";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }
}
