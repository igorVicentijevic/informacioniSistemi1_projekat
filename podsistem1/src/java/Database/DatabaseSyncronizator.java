/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import SharedLibrary.KorisnikDTO;
import Entiteti.Korisnik;
import Entiteti.Mesto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author igor
 */
public class DatabaseSyncronizator{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem1PU");
    
    private EntityManager em;
    public DatabaseSyncronizator(){
        em = emf.createEntityManager();
    }
//    public String perzistirajKorisnika(KorisnikDTO korisnikDTO) {
//        if(em.find(Korisnik.class, korisnikDTO.idK) != null)
//            return "Korisnik je vec u bazi!";
//        return DatabaseHandler.getInstance().perzistirajKorisnika(korisnikDTO);  
//    }
    
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
            
//            Korisnik k = em.find(Korisnik.class, korisnik.getIdK());
//            if(k != null)
//                return "Korisnik sa idK="+k.getIdK()+" vec postoji!";

            Mesto m = em.find(Mesto.class, korisnikDTO.mesto.naziv);
            if(m == null){
                m = new Mesto();
                m.setNaziv(korisnikDTO.mesto.naziv);
            }
            korisnik.setMesto(m);
            

        
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
}
