/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author igor
 */
@Entity
@Table(name = "meseccena")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meseccena.findAll", query = "SELECT m FROM Meseccena m"),
    @NamedQuery(name = "Meseccena.findByIdMC", query = "SELECT m FROM Meseccena m WHERE m.idMC = :idMC"),
    @NamedQuery(name = "Meseccena.findByMesec", query = "SELECT m FROM Meseccena m WHERE m.mesec = :mesec"),
    @NamedQuery(name = "Meseccena.findByCena", query = "SELECT m FROM Meseccena m WHERE m.cena = :cena")})
public class Meseccena implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdMC")
    private Integer idMC;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mesec")
    private int mesec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private float cena;
    @JoinTable(name = "tarifa", joinColumns = {
        @JoinColumn(name = "IdMC", referencedColumnName = "IdMC")}, inverseJoinColumns = {
        @JoinColumn(name = "IdPak", referencedColumnName = "IdPak")})
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Paket> paketList;

    public Meseccena() {
    }

    public Meseccena(Integer idMC) {
        this.idMC = idMC;
    }

    public Meseccena(Integer idMC, int mesec, float cena) {
        this.idMC = idMC;
        this.mesec = mesec;
        this.cena = cena;
    }

    public Integer getIdMC() {
        return idMC;
    }

    public void setIdMC(Integer idMC) {
        this.idMC = idMC;
    }

    public int getMesec() {
        return mesec;
    }

    public void setMesec(int mesec) {
        this.mesec = mesec;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    @XmlTransient
    public List<Paket> getPaketList() {
        return paketList;
    }

    public void setPaketList(List<Paket> paketList) {
        this.paketList = paketList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMC != null ? idMC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meseccena)) {
            return false;
        }
        Meseccena other = (Meseccena) object;
        if ((this.idMC == null && other.idMC != null) || (this.idMC != null && !this.idMC.equals(other.idMC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Meseccena[ idMC=" + idMC + " ]";
    }
    
}
