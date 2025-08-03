package entities;

import SharedLibraryPodsistem3.PretplataDTO;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Pretplata")
public class Pretplata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPre", nullable = false)
    private Integer idPre;

    @ManyToOne
    @JoinColumn(name = "IdK", referencedColumnName = "IdK", nullable = false)
    private Korisnik korisnik;

    @ManyToOne
    @JoinColumn(name = "IdPak", referencedColumnName = "IdPak", nullable = false)
    private Paket paket;

    @Column(name = "datumVremePretplate", nullable = false)
    private Date datumVremePretplate;

    // Getters and Setters
    public Integer getIdPre() {
        return idPre;
    }

    public void setIdPre(Integer idPre) {
        this.idPre = idPre;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public Date getDatumVremePretplate() {
        return datumVremePretplate;
    }

    public void setDatumVremePretplate(Date datumVremePretplate) {
        this.datumVremePretplate = datumVremePretplate;
    }

    public PretplataDTO convertToDTO() {
        PretplataDTO pretplataDTO = new PretplataDTO();
        pretplataDTO.datumVremePretplate = datumVremePretplate;
        pretplataDTO.idPre = idPre;
        pretplataDTO.korisnik = this.getKorisnik().convertToDTO();
        pretplataDTO.paket = this.getPaket().convertToDTO();
        return pretplataDTO;
    }
}
