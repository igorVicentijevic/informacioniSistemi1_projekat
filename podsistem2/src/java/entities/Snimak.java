/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import SharedLibraryPodsistem2.AudioSnimakDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "snimak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Snimak.findAll", query = "SELECT s FROM Snimak s"),
    @NamedQuery(name = "Snimak.findByIdSni", query = "SELECT s FROM Snimak s WHERE s.idSni = :idSni"),
    @NamedQuery(name = "Snimak.findByVremePostavljanja", query = "SELECT s FROM Snimak s WHERE s.vremePostavljanja = :vremePostavljanja"),
    @NamedQuery(name = "Snimak.findByTrajanje", query = "SELECT s FROM Snimak s WHERE s.trajanje = :trajanje"),
    @NamedQuery(name = "Snimak.findByNaziv", query = "SELECT s FROM Snimak s WHERE s.naziv = :naziv")})
public class Snimak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSni")
    private Integer idSni;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vremePostavljanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremePostavljanja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trajanje")
    @Temporal(TemporalType.TIME)
    private Date trajanje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @ManyToMany(mappedBy = "snimakList")
    private List<Kategorija> kategorijaList;
    @JoinColumn(name = "idK_vlasnik", referencedColumnName = "idK")
    @ManyToOne(optional = false)
    private Korisnik idKvlasnik;

    public Snimak() {
    }

    public Snimak(Integer idSni) {
        this.idSni = idSni;
    }

    public Snimak(Integer idSni, Date vremePostavljanja, Date trajanje, String naziv) {
        this.idSni = idSni;
        this.vremePostavljanja = vremePostavljanja;
        this.trajanje = trajanje;
        this.naziv = naziv;
    }

    public Integer getIdSni() {
        return idSni;
    }

    public void setIdSni(Integer idSni) {
        this.idSni = idSni;
    }

    public Date getVremePostavljanja() {
        return vremePostavljanja;
    }

    public void setVremePostavljanja(Date vremePostavljanja) {
        this.vremePostavljanja = vremePostavljanja;
    }

    public Date getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Date trajanje) {
        this.trajanje = trajanje;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public List<Kategorija> getKategorijaList() {
        return kategorijaList;
    }

    public void setKategorijaList(List<Kategorija> kategorijaList) {
        this.kategorijaList = kategorijaList;
    }

    public Korisnik getIdKvlasnik() {
        return idKvlasnik;
    }

    public void setIdKvlasnik(Korisnik idKvlasnik) {
        this.idKvlasnik = idKvlasnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSni != null ? idSni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Snimak)) {
            return false;
        }
        Snimak other = (Snimak) object;
        if ((this.idSni == null && other.idSni != null) || (this.idSni != null && !this.idSni.equals(other.idSni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Snimak[ idSni=" + idSni + " ]";
    }
    public AudioSnimakDTO convertToDTO(){
        AudioSnimakDTO snimakDTO = new AudioSnimakDTO();
        snimakDTO.idSni = idSni;
        snimakDTO.naziv = naziv;
        snimakDTO.trajanje = trajanje;
        snimakDTO.vremePostavljanja = vremePostavljanja;
        snimakDTO.vlasnik = idKvlasnik.convertToDTO();
        return snimakDTO;
    }
}
