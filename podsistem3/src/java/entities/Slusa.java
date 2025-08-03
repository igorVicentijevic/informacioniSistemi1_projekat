/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import SharedLibraryPodsistem3.SlusaDTO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "slusa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slusa.findAll", query = "SELECT s FROM Slusa s"),
    @NamedQuery(name = "Slusa.findByIdSlu", query = "SELECT s FROM Slusa s WHERE s.idSlu = :idSlu"),
    @NamedQuery(name = "Slusa.findByDatumVremePocetkaSlusanja", query = "SELECT s FROM Slusa s WHERE s.datumVremePocetkaSlusanja = :datumVremePocetkaSlusanja"),
    @NamedQuery(name = "Slusa.findBySekundaOdKojeJePoceloSlusanje", query = "SELECT s FROM Slusa s WHERE s.sekundaOdKojeJePoceloSlusanje = :sekundaOdKojeJePoceloSlusanje"),
    @NamedQuery(name = "Slusa.findByOdslusanoUSekundama", query = "SELECT s FROM Slusa s WHERE s.odslusanoUSekundama = :odslusanoUSekundama")})
public class Slusa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSlu")
    private Integer idSlu;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumVremePocetkaSlusanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVremePocetkaSlusanja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sekundaOdKojeJePoceloSlusanje")
    private int sekundaOdKojeJePoceloSlusanje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "odslusanoUSekundama")
    private int odslusanoUSekundama;
    @JoinColumns({
        @JoinColumn(name = "IdK", referencedColumnName = "IdK"),
        @JoinColumn(name = "IdK", referencedColumnName = "IdK")})
    @ManyToOne(optional = false)
    private Korisnik korisnik;
    @JoinColumn(name = "IdSni", referencedColumnName = "IdSni")
    @ManyToOne(optional = false)
    private Snimak idSni;

    public Slusa() {
    }

    public Slusa(Integer idSlu) {
        this.idSlu = idSlu;
    }

    public Slusa(Integer idSlu, Date datumVremePocetkaSlusanja, int sekundaOdKojeJePoceloSlusanje, int odslusanoUSekundama) {
        this.idSlu = idSlu;
        this.datumVremePocetkaSlusanja = datumVremePocetkaSlusanja;
        this.sekundaOdKojeJePoceloSlusanje = sekundaOdKojeJePoceloSlusanje;
        this.odslusanoUSekundama = odslusanoUSekundama;
    }

    public Integer getIdSlu() {
        return idSlu;
    }

    public void setIdSlu(Integer idSlu) {
        this.idSlu = idSlu;
    }

    public Date getDatumVremePocetkaSlusanja() {
        return datumVremePocetkaSlusanja;
    }

    public void setDatumVremePocetkaSlusanja(Date datumVremePocetkaSlusanja) {
        this.datumVremePocetkaSlusanja = datumVremePocetkaSlusanja;
    }

    public int getSekundaOdKojeJePoceloSlusanje() {
        return sekundaOdKojeJePoceloSlusanje;
    }

    public void setSekundaOdKojeJePoceloSlusanje(int sekundaOdKojeJePoceloSlusanje) {
        this.sekundaOdKojeJePoceloSlusanje = sekundaOdKojeJePoceloSlusanje;
    }

    public int getOdslusanoUSekundama() {
        return odslusanoUSekundama;
    }

    public void setOdslusanoUSekundama(int odslusanoUSekundama) {
        this.odslusanoUSekundama = odslusanoUSekundama;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Snimak getIdSni() {
        return idSni;
    }

    public void setIdSni(Snimak idSni) {
        this.idSni = idSni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSlu != null ? idSlu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slusa)) {
            return false;
        }
        Slusa other = (Slusa) object;
        if ((this.idSlu == null && other.idSlu != null) || (this.idSlu != null && !this.idSlu.equals(other.idSlu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Slusa[ idSlu=" + idSlu + " ]";
    }
    
    
    public SlusaDTO convertToDTO(){
        SlusaDTO slusaDTO =  new SlusaDTO();
        slusaDTO.idK = this.getKorisnik().getIdK();
        slusaDTO.idSni = this.getIdSni().getIdSni();
        slusaDTO.idS = this.getIdSlu();
        slusaDTO.datumVremePocetkaSlusanja = this.getDatumVremePocetkaSlusanja();
        slusaDTO.odslusanoUSekundama = this.getOdslusanoUSekundama();
        slusaDTO.sekundaOdKojeJePoceloSlusanje = this.getSekundaOdKojeJePoceloSlusanje();
        return slusaDTO;
        
    }
}
