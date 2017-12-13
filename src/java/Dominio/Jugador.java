/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author joselm
 */
@Entity
@Table(name = "JUGADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findById", query = "SELECT j FROM Jugador j WHERE j.id = :id"),
    @NamedQuery(name = "Jugador.findByName", query = "SELECT j FROM Jugador j WHERE j.name = :name"),
    @NamedQuery(name = "Jugador.findByPosition", query = "SELECT j FROM Jugador j WHERE j.position = :position"),
    @NamedQuery(name = "Jugador.findByJerseynumber", query = "SELECT j FROM Jugador j WHERE j.jerseynumber = :jerseynumber"),
    @NamedQuery(name = "Jugador.findByDateofbirth", query = "SELECT j FROM Jugador j WHERE j.dateofbirth = :dateofbirth"),
    @NamedQuery(name = "Jugador.findByNationality", query = "SELECT j FROM Jugador j WHERE j.nationality = :nationality"),
    @NamedQuery(name = "Jugador.findByContractuntil", query = "SELECT j FROM Jugador j WHERE j.contractuntil = :contractuntil"),
    @NamedQuery(name = "Jugador.findByMarketvalue", query = "SELECT j FROM Jugador j WHERE j.marketvalue = :marketvalue")})
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "POSITION")
    private String position;
    @Column(name = "JERSEYNUMBER")
    private Integer jerseynumber;
    @Size(max = 20)
    @Column(name = "DATEOFBIRTH")
    private String dateofbirth;
    @Size(max = 20)
    @Column(name = "NATIONALITY")
    private String nationality;
    @Size(max = 20)
    @Column(name = "CONTRACTUNTIL")
    private String contractuntil;
    @Size(max = 20)
    @Column(name = "MARKETVALUE")
    private String marketvalue;
    @OneToMany(mappedBy = "jugId")
    private List<Equipo> equipoList;

    public Jugador() {
    }

    public Jugador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseynumber() {
        return jerseynumber;
    }

    public void setJerseynumber(Integer jerseynumber) {
        this.jerseynumber = jerseynumber;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContractuntil() {
        return contractuntil;
    }

    public void setContractuntil(String contractuntil) {
        this.contractuntil = contractuntil;
    }

    public String getMarketvalue() {
        return marketvalue;
    }

    public void setMarketvalue(String marketvalue) {
        this.marketvalue = marketvalue;
    }

    @XmlTransient
    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Jugador[ id=" + id + " ]";
    }
    
}
