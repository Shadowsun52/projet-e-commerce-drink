/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "TYPEDRINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typedrink.findAll", query = "SELECT t FROM Typedrink t"),
    @NamedQuery(name = "Typedrink.findByIddrink", query = "SELECT t FROM Typedrink t WHERE t.typedrinkPK.iddrink = :iddrink"),
    @NamedQuery(name = "Typedrink.findByIdtype", query = "SELECT t FROM Typedrink t WHERE t.typedrinkPK.idtype = :idtype")})
public class Typedrink implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TypedrinkPK typedrinkPK;
    @JoinColumn(name = "IDDRINK", referencedColumnName = "IDDRINK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drink drink;
    @JoinColumn(name = "IDTYPE", referencedColumnName = "IDTYPE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type;

    public Typedrink() {
    }

    public Typedrink(TypedrinkPK typedrinkPK) {
        this.typedrinkPK = typedrinkPK;
    }

    public Typedrink(int iddrink, int idtype) {
        this.typedrinkPK = new TypedrinkPK(iddrink, idtype);
    }

    public TypedrinkPK getTypedrinkPK() {
        return typedrinkPK;
    }

    public void setTypedrinkPK(TypedrinkPK typedrinkPK) {
        this.typedrinkPK = typedrinkPK;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typedrinkPK != null ? typedrinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typedrink)) {
            return false;
        }
        Typedrink other = (Typedrink) object;
        if ((this.typedrinkPK == null && other.typedrinkPK != null) || (this.typedrinkPK != null && !this.typedrinkPK.equals(other.typedrinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Typedrink[ typedrinkPK=" + typedrinkPK + " ]";
    }
    
}
