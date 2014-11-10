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
public class LangCountryPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOUNTRY")
    private int idcountry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;

    public LangCountryPK() {
    }

    public LangCountryPK(int idcountry, int idlanguage) {
        this.idcountry = idcountry;
        this.idlanguage = idlanguage;
    }

    public int getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(int idcountry) {
        this.idcountry = idcountry;
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
        hash += (int) idcountry;
        hash += (int) idlanguage;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangCountryPK)) {
            return false;
        }
        LangCountryPK other = (LangCountryPK) object;
        if (this.idcountry != other.idcountry) {
            return false;
        }
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangCountryPK[ idcountry=" + idcountry + ", idlanguage=" + idlanguage + " ]";
    }
    
}
