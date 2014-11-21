/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "LINE_ORDER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineOrder.findAll", query = "SELECT l FROM LineOrder l"),
    @NamedQuery(name = "LineOrder.findByIddrink", query = "SELECT l FROM LineOrder l WHERE l.lineOrderPK.iddrink = :iddrink"),
    @NamedQuery(name = "LineOrder.findByNumorder", query = "SELECT l FROM LineOrder l WHERE l.lineOrderPK.numorder = :numorder"),
    @NamedQuery(name = "LineOrder.findByQuantity", query = "SELECT l FROM LineOrder l WHERE l.quantity = :quantity"),
    @NamedQuery(name = "LineOrder.findByPrice", query = "SELECT l FROM LineOrder l WHERE l.price = :price")})
public class LineOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LineOrderPK lineOrderPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private short quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;
    @JoinColumn(name = "IDDRINK", referencedColumnName = "IDDRINK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drink drink;
    @JoinColumn(name = "NUMORDER", referencedColumnName = "NUMORDER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OrderTable orderTable;

    public LineOrder() {
    }

    public LineOrder(LineOrderPK lineOrderPK) {
        this.lineOrderPK = lineOrderPK;
    }

    public LineOrder(LineOrderPK lineOrderPK, short quantity, BigDecimal price) {
        this.lineOrderPK = lineOrderPK;
        this.quantity = quantity;
        this.price = price;
    }

    public LineOrder(int iddrink, int numorder) {
        this.lineOrderPK = new LineOrderPK(iddrink, numorder);
    }

    public LineOrderPK getLineOrderPK() {
        return lineOrderPK;
    }

    public void setLineOrderPK(LineOrderPK lineOrderPK) {
        this.lineOrderPK = lineOrderPK;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(OrderTable orderTable) {
        this.orderTable = orderTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineOrderPK != null ? lineOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineOrder)) {
            return false;
        }
        LineOrder other = (LineOrder) object;
        if ((this.lineOrderPK == null && other.lineOrderPK != null) || (this.lineOrderPK != null && !this.lineOrderPK.equals(other.lineOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LineOrder[ lineOrderPK=" + lineOrderPK + " ]";
    }
    
}
