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
public class LangDrinkPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDRINK")
    private int iddrink;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;

    public LangDrinkPK() {
    }

    public LangDrinkPK(int iddrink, int idlanguage) {
        this.iddrink = iddrink;
        this.idlanguage = idlanguage;
    }

    public int getIddrink() {
        return iddrink;
    }

    public void setIddrink(int iddrink) {
        this.iddrink = iddrink;
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
        hash += (int) iddrink;
        hash += (int) idlanguage;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangDrinkPK)) {
            return false;
        }
        LangDrinkPK other = (LangDrinkPK) object;
        if (this.iddrink != other.iddrink) {
            return false;
        }
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangDrinkPK[ iddrink=" + iddrink + ", idlanguage=" + idlanguage + " ]";
    }
    
}
