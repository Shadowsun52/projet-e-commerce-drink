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
public class LangCatPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCATEGORY")
    private int idcategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;

    public LangCatPK() {
    }

    public LangCatPK(int idcategory, int idlanguage) {
        this.idcategory = idcategory;
        this.idlanguage = idlanguage;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
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
        hash += (int) idcategory;
        hash += (int) idlanguage;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangCatPK)) {
            return false;
        }
        LangCatPK other = (LangCatPK) object;
        if (this.idcategory != other.idcategory) {
            return false;
        }
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangCatPK[ idcategory=" + idcategory + ", idlanguage=" + idlanguage + " ]";
    }
    
}
