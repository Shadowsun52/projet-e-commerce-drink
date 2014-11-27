/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alex
 */
@Embeddable
public class TypedrinkPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDRINK")
    private int iddrink;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTYPE")
    private int idtype;

    public TypedrinkPK() {
    }

    public TypedrinkPK(int iddrink, int idtype) {
        this.iddrink = iddrink;
        this.idtype = idtype;
    }

    public int getIddrink() {
        return iddrink;
    }

    public void setIddrink(int iddrink) {
        this.iddrink = iddrink;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddrink;
        hash += (int) idtype;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypedrinkPK)) {
            return false;
        }
        TypedrinkPK other = (TypedrinkPK) object;
        if (this.iddrink != other.iddrink) {
            return false;
        }
        if (this.idtype != other.idtype) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.TypedrinkPK[ iddrink=" + iddrink + ", idtype=" + idtype + " ]";
    }
    
}
