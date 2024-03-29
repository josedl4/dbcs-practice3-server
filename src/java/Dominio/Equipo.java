package Dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EQUIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findById", query = "SELECT e FROM Equipo e WHERE e.id = :id"),
    @NamedQuery(name = "Equipo.findByUserAndPlayer", 
            query = "SELECT e FROM Equipo e WHERE e.jugId.id = :player "
                    + "AND e.usId.id = :coach")})
public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "JUG_ID", referencedColumnName = "ID")
    @ManyToOne
    private Jugador jugId;
    @JoinColumn(name = "US_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usId;

    public Equipo() {
    }

    public Equipo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Jugador getJugId() {
        return jugId;
    }

    public void setJugId(Jugador jugId) {
        this.jugId = jugId;
    }

    public Usuario getUsId() {
        return usId;
    }

    public void setUsId(Usuario usId) {
        this.usId = usId;
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
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Equipo[ id=" + id + " ]";
    }
    
}
