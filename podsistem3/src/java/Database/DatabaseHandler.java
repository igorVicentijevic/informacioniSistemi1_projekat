/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import SharedLibraryPodsistem3.OcenaDTO;
import SharedLibraryPodsistem3.PaketDTO;
import SharedLibraryPodsistem3.SlusaDTO;
import entities.Korisnik;
import entities.Meseccena;
import entities.Ocena;
import entities.Paket;
import entities.Snimak;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
import entities.Pretplata;
import entities.Slusa;
/**
 *
 * @author igor
 */
public class DatabaseHandler {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("podsistem3PU");
    
    private EntityManager em;
    public DatabaseHandler(){
        em = emf.createEntityManager();
    }
    
    public String perzistirajPaket(PaketDTO paketDTO){
        try{
            em.getTransaction().begin();
            
            //TypedQuery<Kategorija> kategorija = em.createNamedQuery("Kategorija.findByNaziv", Kategorija.class);
            
            Paket paket = new Paket();
            
            if(paketDTO.idPak != null){
                Paket p = em.find(Paket.class,paketDTO.idPak);
                if(p != null){
                    return "Paket sa idP="+p.getIdPak()+" vec postoji!";
                }
            }
          
            
            if(paketDTO.idPak!=null)
                paket.setIdPak(paketDTO.idPak);
            
            
            List<Meseccena> mojiMesecCena = new ArrayList<>();
            
            for(int i = 1; i <= 12; i++){
                Meseccena mc = new Meseccena();
                mc.setMesec(i); 
                mc.setCena(0);  
                mojiMesecCena.add(mc);
                List<Paket> mojiPaketi = new ArrayList<>();
                mojiPaketi.add(paket);
                mc.setPaketList(mojiPaketi);
                em.persist(mc);
                
            }
            paket.setMeseccenaList(mojiMesecCena);
            em.persist(paket);
            
            em.getTransaction().commit();
            return "Paked uspesno dodat u bazu!";
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public String dodajPretplatu(int idK, int idPak, Date datumVremePretplate) {
        try{
            em.getTransaction().begin();
            
            Korisnik korisnik = em.find(Korisnik.class, idK);
            if(korisnik == null)
                return "Korisnik ne postoji u bazi!";
            
            Paket paket = em.find(Paket.class, idPak);
            if(paket == null)
                return "Paket ne postoji u bazi!";
            
            Pretplata pretplata = new Pretplata();
            pretplata.setKorisnik(korisnik);
            pretplata.setPaket(paket);
            pretplata.setDatumVremePretplate(datumVremePretplate);
            em.persist(pretplata);
            
            em.getTransaction().commit();
            return "Pretplata uspesno dodata!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public String promeniCenuPaketa(int idPak, float cena) {
        try{
            em.getTransaction().begin();
            
            Paket paket = em.find(Paket.class, idPak);
            if(paket == null)
                return "Paket ne postoji u bazi!";
            
           
            
            for(Meseccena mc: paket.getMeseccenaList()){
                mc.setCena(cena);
            }
           
            
            em.getTransaction().commit();
            return "Mesecna cena uspesno promenjena!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public String dodajSlusanje(SlusaDTO slusaDTO) {
        try{
            em.getTransaction().begin();
            
            Snimak snimak = em.find(Snimak.class,slusaDTO.idSni);
            if(snimak == null)
                return "Snimak ne postoji u bazi!";
            
            Korisnik korisnik = em.find(Korisnik.class,slusaDTO.idK);
            if(korisnik == null)
                return "Korisnik ne postoji u bazi!";
            Slusa slusa = new Slusa();
            slusa.setKorisnik(korisnik);
            slusa.setIdSni(snimak);
            slusa.setDatumVremePocetkaSlusanja(slusaDTO.datumVremePocetkaSlusanja);
            slusa.setSekundaOdKojeJePoceloSlusanje(slusaDTO.sekundaOdKojeJePoceloSlusanje);
            slusa.setOdslusanoUSekundama(slusaDTO.odslusanoUSekundama);
            em.persist(slusa);
            em.getTransaction().commit();
            return "Slusanje dodato!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }
    
    public String dodajSnimakUOmiljene(int idK, int idSni){
        try{
            em.getTransaction().begin();
            
            Korisnik korisnik = em.find(Korisnik.class, idK);
            if(korisnik == null)
                return "Korisnik ne postoji u bazi!";
            
            Snimak snimak = em.find(Snimak.class, idSni);
            if(snimak == null)
                return "Snimak ne postoji u bazi!";
            
            List<Snimak> omiljeniSnimci = korisnik.getSnimakList();
            if(omiljeniSnimci == null)
            {
                omiljeniSnimci = new ArrayList<>();
                korisnik.setSnimakList(omiljeniSnimci);
            }
            omiljeniSnimci.add(snimak);
            
            em.getTransaction().commit();
            return "Omiljeni snimak dodat za korisnika!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public String dodajOcenu(OcenaDTO ocenaDTO) {
        try{
            em.getTransaction().begin();
            
            Korisnik korisnik = em.find(Korisnik.class, ocenaDTO.idK);
            if(korisnik == null)
                return "Korisnik ne postoji u bazi!";
            
            Snimak snimak = em.find(Snimak.class, ocenaDTO.idSni);
            if(snimak == null)
                return "Snimak ne postoji u bazi!";
            
            Ocena ocena = new Ocena();
            ocena.setKorisnik(korisnik);
            ocena.setIdSni(snimak);
            ocena.setDatumVremeDavanjaOcena(ocenaDTO.datumVremeDavanjaOcena);
            ocena.setOcena(ocenaDTO.ocena);
            
            em.persist(ocena);
            
            em.getTransaction().commit();
            return "Ocena dodata u bazu!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public String promeniOcenu(OcenaDTO ocenaDTO) {
        try{
            em.getTransaction().begin();
           
            Ocena ocena = em.find(Ocena.class, ocenaDTO.idOce);
            if(ocena == null)
                return "Ocena ne postoji u bazi!";
            
            ocena.setOcena(ocenaDTO.ocena);
            em.getTransaction().commit();
            return "Ocena uspesno promenjena!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public String izbrisiOcenu(OcenaDTO ocenaDTO) {
        try{
            em.getTransaction().begin();
           
            Ocena ocena = em.find(Ocena.class, ocenaDTO.idOce);
            if(ocena == null)
                return "Ocena ne postoji u bazi!";
            
            em.remove(ocena);
            
            em.getTransaction().commit();
            return "Ocena uspesno izbrisana iz baze!";
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
               return "Doslo je do greske!";
        }
        finally{
            em.close();
        }
    }

    public List<Paket> dohvatiSvePakete() {
        try{
            em.getTransaction().begin();
           
            String query = "SELECT p FROM Paket p";
            TypedQuery<Paket> sviPaketiTQ = em.createQuery(query, Paket.class);
            List<Paket> paketi = sviPaketiTQ.getResultList();
            
            em.getTransaction().commit();
            return paketi;
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
             
        }
        finally{
            em.close();
        }
        return null;
    }

    public List<Slusa> dohvatiSvaSlusanjaZaAudioSnimak(int idSni) {
        try{
            em.getTransaction().begin();
           
            Snimak snimak = em.find(Snimak.class, idSni);
            if(snimak == null)
                return null;
            
            List<Slusa> slusanja = snimak.getSlusaList();
            
            
            em.getTransaction().commit();
            
            return slusanja;
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
             
        }
        finally{
            em.close();
        }
        return null;
    }

    public List<Ocena> dohvatiSveOceneZaAudioSnimak(int idSni) {
        try{
            em.getTransaction().begin();
           
            Snimak snimak = em.find(Snimak.class, idSni);
            if(snimak == null)
                return null;
            
            List<Ocena> ocene = snimak.getOcenaList();
            
            
            em.getTransaction().commit();
            
            return ocene;
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
             
        }
        finally{
            em.close();
        }
        return null;
    }

    public List<Snimak> dohvatiOmiljeneSnimkeZaKorisnika(int idK) {
       try{
            em.getTransaction().begin();
           
            Korisnik korisnik = em.find(Korisnik.class, idK);
            if(korisnik == null)
                return null;
            
            List<Snimak> omiljeniSnimci = korisnik.getSnimakList();
            
            
            em.getTransaction().commit();
            
            return omiljeniSnimci;
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
             
        }
        finally{
            em.close();
        }
        return null;
    }

    public List<Pretplata> dohvatiPretplateZaKorisnika(int idK) {
        try{
            em.getTransaction().begin();
           
            Korisnik korisnik = em.find(Korisnik.class, idK);
            if(korisnik == null)
                return null;
            
            String query = "SELECT p FROM Pretplata p WHERE p.korisnik=:korisnik";
            TypedQuery<Pretplata> pretplataTQ = em.createQuery(query,Pretplata.class);
            pretplataTQ.setParameter("korisnik", korisnik);
            List<Pretplata> pretplate = pretplataTQ.getResultList();
            
            
            em.getTransaction().commit();
            
            return pretplate;
           
        }
        catch(Exception e){
               System.out.println(e.getMessage());
             
        }
        finally{
            em.close();
        }
        return null;
    }

    
    
}