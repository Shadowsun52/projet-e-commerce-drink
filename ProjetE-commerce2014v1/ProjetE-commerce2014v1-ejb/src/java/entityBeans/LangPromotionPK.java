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
public class LangPromotionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDLANGUAGE")
    private int idlanguage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPROMOTION")
    private int idpromotion;

    public LangPromotionPK() {
    }

    public LangPromotionPK(int idlanguage, int idpromotion) {
        this.idlanguage = idlanguage;
        this.idpromotion = idpromotion;
    }

    public int getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(int idlanguage) {
        this.idlanguage = idlanguage;
    }

    public int getIdpromotion() {
        return idpromotion;
    }

    public void setIdpromotion(int idpromotion) {
        this.idpromotion = idpromotion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idlanguage;
        hash += (int) idpromotion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangPromotionPK)) {
            return false;
        }
        LangPromotionPK other = (LangPromotionPK) object;
        if (this.idlanguage != other.idlanguage) {
            return false;
        }
        if (this.idpromotion != other.idpromotion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangPromotionPK[ idlanguage=" + idlanguage + ", idpromotion=" + idpromotion + " ]";
    }
    
}
