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
public class LineOrderPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDRINK")
    private int iddrink;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMORDER")
    private int numorder;

    public LineOrderPK() {
    }

    public LineOrderPK(int iddrink, int numorder) {
        this.iddrink = iddrink;
        this.numorder = numorder;
    }

    public int getIddrink() {
        return iddrink;
    }

    public void setIddrink(int iddrink) {
        this.iddrink = iddrink;
    }

    public int getNumorder() {
        return numorder;
    }

    public void setNumorder(int numorder) {
        this.numorder = numorder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddrink;
        hash += (int) numorder;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineOrderPK)) {
            return false;
        }
        LineOrderPK other = (LineOrderPK) object;
        if (this.iddrink != other.iddrink) {
            return false;
        }
        if (this.numorder != other.numorder) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LineOrderPK[ iddrink=" + iddrink + ", numorder=" + numorder + " ]";
    }
    
}
