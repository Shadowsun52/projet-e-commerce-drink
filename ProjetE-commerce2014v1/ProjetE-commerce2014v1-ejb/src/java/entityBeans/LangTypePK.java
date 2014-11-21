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
 * @author Alexandre
 */
@Embeddable
public class LangTypePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTYPE")
    private int idtype;

    public LangTypePK() {
    }

    public LangTypePK(int idlanguage, int idtype) {
        this.idlanguage = idlanguage;
        this.idtype = idtype;
    }

    public int getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(int idlanguage) {
        this.idlanguage = idlanguage;
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
        hash += (int) idlanguage;
        hash += (int) idtype;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangTypePK)) {
            return false;
        }
        LangTypePK other = (LangTypePK) object;
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        if (this.idtype != other.idtype) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangTypePK[ idlanguage=" + idlanguage + ", idtype=" + idtype + " ]";
    }
    
}
