/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import SharedLibrary.KorisnikDTO;
import SharedLibrary.MestoDTO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByIdK", query = "SELECT k FROM Korisnik k WHERE k.idK = :idK"),
    @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime"),
    @NamedQuery(name = "Korisnik.findByEmail", query = "SELECT k FROM Korisnik k WHERE k.email = :email"),
    @NamedQuery(name = "Korisnik.findByGodiste", query = "SELECT k FROM Korisnik k WHERE k.godiste = :godiste"),
    @NamedQuery(name = "Korisnik.findByPol", query = "SELECT k FROM Korisnik k WHERE k.pol = :pol"),
    @NamedQuery(name = "Korisnik.findByMesto", query = "SELECT k FROM Korisnik k WHERE k.mesto = :mesto")})
public class Korisnik implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ime")
    private String ime;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "godiste")
    private int godiste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pol")
    private String pol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mesto")
    private String mesto;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdK")
    private Integer idK;
    @ManyToMany
    private List<Paket> paketList;
    @JoinTable(name = "omiljeni", joinColumns = {
        @JoinColumn(name = "IdK", referencedColumnName = "IdK"),
        @JoinColumn(name = "IdK", referencedColumnName = "IdK")}, inverseJoinColumns = {
        @JoinColumn(name = "IdSni", referencedColumnName = "IdSni")})
    @ManyToMany
    private List<Snimak> snimakList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKvlasnik")
    private List<Snimak> snimakList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korisnik")
    private List<Slusa> slusaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "korisnik")
    private List<Ocena> ocenaList;

    public Korisnik() {
    }

    public Korisnik(Integer idK) {
        this.idK = idK;
    }

    public Korisnik(Integer idK, String ime, String email, int godiste, String pol, String mesto) {
        this.idK = idK;
        this.ime = ime;
        this.email = email;
        this.godiste = godiste;
        this.pol = pol;
        this.mesto = mesto;
    }

    public Integer getIdK() {
        return idK;
    }

    public void setIdK(Integer idK) {
        this.idK = idK;
    }


    @XmlTransient
    public List<Paket> getPaketList() {
        return paketList;
    }

    public void setPaketList(List<Paket> paketList) {
        this.paketList = paketList;
    }

    @XmlTransient
    public List<Snimak> getSnimakList() {
        return snimakList;
    }

    public void setSnimakList(List<Snimak> snimakList) {
        this.snimakList = snimakList;
    }

    @XmlTransient
    public List<Snimak> getSnimakList1() {
        return snimakList1;
    }

    public void setSnimakList1(List<Snimak> snimakList1) {
        this.snimakList1 = snimakList1;
    }

    @XmlTransient
    public List<Slusa> getSlusaList() {
        return slusaList;
    }

    public void setSlusaList(List<Slusa> slusaList) {
        this.slusaList = slusaList;
    }

    @XmlTransient
    public List<Ocena> getOcenaList() {
        return ocenaList;
    }

    public void setOcenaList(List<Ocena> ocenaList) {
        this.ocenaList = ocenaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idK != null ? idK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.idK == null && other.idK != null) || (this.idK != null && !this.idK.equals(other.idK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Korisnik[ idK=" + idK + " ]";
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }
    
    public KorisnikDTO convertToDTO(){
        KorisnikDTO korisnikDTO = new KorisnikDTO();
        korisnikDTO.idK = idK;
        korisnikDTO.godiste = godiste;
        korisnikDTO.email = email;
        korisnikDTO.ime = ime;
        korisnikDTO.pol = pol;
        MestoDTO mestoDTO = new MestoDTO();
        mestoDTO.naziv = mesto;
        korisnikDTO.mesto = mestoDTO;
        return korisnikDTO;
    }
    
}
