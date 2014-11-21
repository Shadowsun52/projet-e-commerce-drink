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
public class LangDelmodePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDELIVERYMODE")
    private int iddeliverymode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;

    public LangDelmodePK() {
    }

    public LangDelmodePK(int iddeliverymode, int idlanguage) {
        this.iddeliverymode = iddeliverymode;
        this.idlanguage = idlanguage;
    }

    public int getIddeliverymode() {
        return iddeliverymode;
    }

    public void setIddeliverymode(int iddeliverymode) {
        this.iddeliverymode = iddeliverymode;
    }

    public int getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(int idlanguage) {
        this.idlanguage = idlanguage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddeliverymode;
        hash += (int) idlanguage;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangDelmodePK)) {
            return false;
        }
        LangDelmodePK other = (LangDelmodePK) object;
        if (this.iddeliverymode != other.iddeliverymode) {
            return false;
        }
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangDelmodePK[ iddeliverymode=" + iddeliverymode + ", idlanguage=" + idlanguage + " ]";
    }
    
}
