/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import SharedLibraryPodsistem3.PaketDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "paket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paket.findAll", query = "SELECT p FROM Paket p"),
    @NamedQuery(name = "Paket.findByIdPak", query = "SELECT p FROM Paket p WHERE p.idPak = :idPak")})
public class Paket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdPak")
    private Integer idPak;
    @Column(name="naziv")
    private String naziv = "naziv";
    @ManyToMany(mappedBy = "paketList")
    private List<Meseccena> meseccenaList;
    @ManyToMany(mappedBy = "paketList")
    private List<Korisnik> korisnikList;

    public Paket() {
    }

    public Paket(Integer idPak) {
        this.idPak = idPak;
    }

    public Integer getIdPak() {
        return idPak;
    }

    public void setIdPak(Integer idPak) {
        this.idPak = idPak;
    }

    @XmlTransient
    public List<Meseccena> getMeseccenaList() {
        return meseccenaList;
    }

    public void setMeseccenaList(List<Meseccena> meseccenaList) {
        this.meseccenaList = meseccenaList;
    }

    @XmlTransient
    public List<Korisnik> getKorisnikList() {
        return korisnikList;
    }

    public void setKorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPak != null ? idPak.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paket)) {
            return false;
        }
        Paket other = (Paket) object;
        if ((this.idPak == null && other.idPak != null) || (this.idPak != null && !this.idPak.equals(other.idPak))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Paket[ idPak=" + idPak + " ]";
    }
    
    public PaketDTO convertToDTO(){
        PaketDTO paketDTO = new PaketDTO();
        paketDTO.idPak = idPak;
        return paketDTO;
    }
    
}
