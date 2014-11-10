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
public class LangLocPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLOCALITY")
    private int idlocality;

    public LangLocPK() {
    }

    public LangLocPK(int idlanguage, int idlocality) {
        this.idlanguage = idlanguage;
        this.idlocality = idlocality;
    }

    public int getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(int idlanguage) {
        this.idlanguage = idlanguage;
    }

    public int getIdlocality() {
        return idlocality;
    }

    public void setIdlocality(int idlocality) {
        this.idlocality = idlocality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idlanguage;
        hash += (int) idlocality;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangLocPK)) {
            return false;
        }
        LangLocPK other = (LangLocPK) object;
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        if (this.idlocality != other.idlocality) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangLocPK[ idlanguage=" + idlanguage + ", idlocality=" + idlocality + " ]";
    }
    
}
