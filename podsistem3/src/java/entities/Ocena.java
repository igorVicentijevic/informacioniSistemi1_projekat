/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import SharedLibraryPodsistem3.OcenaDTO;
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
@Table(name = "ocena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ocena.findAll", query = "SELECT o FROM Ocena o"),
    @NamedQuery(name = "Ocena.findByIdOce", query = "SELECT o FROM Ocena o WHERE o.idOce = :idOce"),
    @NamedQuery(name = "Ocena.findByOcena", query = "SELECT o FROM Ocena o WHERE o.ocena = :ocena"),
    @NamedQuery(name = "Ocena.findByDatumVremeDavanjaOcena", query = "SELECT o FROM Ocena o WHERE o.datumVremeDavanjaOcena = :datumVremeDavanjaOcena")})
public class Ocena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOce")
    private Integer idOce;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocena")
    private int ocena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datumVremeDavanjaOcena")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVremeDavanjaOcena;
    @JoinColumns({
        @JoinColumn(name = "IdK", referencedColumnName = "IdK"),
        @JoinColumn(name = "IdK", referencedColumnName = "IdK")})
    @ManyToOne(optional = false)
    private Korisnik korisnik;
    @JoinColumn(name = "IdSni", referencedColumnName = "IdSni")
    @ManyToOne(optional = false)
    private Snimak idSni;

    public Ocena() {
    }

    public Ocena(Integer idOce) {
        this.idOce = idOce;
    }

    public Ocena(Integer idOce, int ocena, Date datumVremeDavanjaOcena) {
        this.idOce = idOce;
        this.ocena = ocena;
        this.datumVremeDavanjaOcena = datumVremeDavanjaOcena;
    }

    public Integer getIdOce() {
        return idOce;
    }

    public void setIdOce(Integer idOce) {
        this.idOce = idOce;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Date getDatumVremeDavanjaOcena() {
        return datumVremeDavanjaOcena;
    }

    public void setDatumVremeDavanjaOcena(Date datumVremeDavanjaOcena) {
        this.datumVremeDavanjaOcena = datumVremeDavanjaOcena;
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
        hash += (idOce != null ? idOce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocena)) {
            return false;
        }
        Ocena other = (Ocena) object;
        if ((this.idOce == null && other.idOce != null) || (this.idOce != null && !this.idOce.equals(other.idOce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ocena[ idOce=" + idOce + " ]";
    }
    
    public OcenaDTO convertToDTO(){
        OcenaDTO ocenaDTO = new OcenaDTO();
        ocenaDTO.datumVremeDavanjaOcena = this.datumVremeDavanjaOcena;
        ocenaDTO.idK = korisnik.getIdK();
        ocenaDTO.idOce = this.idOce;
        ocenaDTO.idSni = this.getIdSni().getIdSni();
        ocenaDTO.ocena = this.ocena;
        return ocenaDTO;
    }
    
}
