/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "ORDER_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderTable.findAll", query = "SELECT o FROM OrderTable o"),
    @NamedQuery(name = "OrderTable.findByNumorder", query = "SELECT o FROM OrderTable o WHERE o.numorder = :numorder"),
    @NamedQuery(name = "OrderTable.findByValidationdate", query = "SELECT o FROM OrderTable o WHERE o.validationdate = :validationdate"),
    @NamedQuery(name = "OrderTable.findByPaymentdate", query = "SELECT o FROM OrderTable o WHERE o.paymentdate = :paymentdate"),
    @NamedQuery(name = "OrderTable.findByPostalcharges", query = "SELECT o FROM OrderTable o WHERE o.postalcharges = :postalcharges"),
    @NamedQuery(name = "OrderTable.findByDelAddress", query = "SELECT o FROM OrderTable o WHERE o.delAddress = :delAddress"),
    @NamedQuery(name = "OrderTable.findByCustomer", query ="SELECT o FROM OrderTable o WHERE o.idcustomer = :idcustomer")})
public class OrderTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMORDER")
    private Integer numorder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALIDATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date validationdate;
    @Column(name = "PAYMENTDATE")
    @Temporal(TemporalType.DATE)
    private Date paymentdate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSTALCHARGES")
    private BigDecimal postalcharges;
    @JoinTable(name = "PROMOTIONUSING", joinColumns = {
        @JoinColumn(name = "NUMORDER", referencedColumnName = "NUMORDER")}, inverseJoinColumns = {
        @JoinColumn(name = "IDPROMOTION", referencedColumnName = "IDPROMOTION")})
    @ManyToMany
    private Collection<Promotion> promotionCollection;
    @JoinColumn(name = "DEL_ADDRESS", referencedColumnName = "IDADDRESS")
    @ManyToOne(optional = false)
    private Address delAddress;
    @JoinColumn(name = "IDCUSTOMER", referencedColumnName = "IDCUSTOMER")
    @ManyToOne(optional = false)
    private Customer idcustomer;
    @JoinColumn(name = "IDDELIVERYMODE", referencedColumnName = "IDDELIVERYMODE")
    @ManyToOne(optional = false)
    private Deliverymode iddeliverymode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderTable")
    private Collection<LineOrder> lineOrderCollection;

    public OrderTable() {
    }

    public OrderTable(Integer numorder) {
        this.numorder = numorder;
    }

    public OrderTable(Integer numorder, Date validationdate, BigDecimal postalcharges) {
        this.numorder = numorder;
        this.validationdate = validationdate;
        this.postalcharges = postalcharges;
    }

    public Integer getNumorder() {
        return numorder;
    }

    public void setNumorder(Integer numorder) {
        this.numorder = numorder;
    }

    public Date getValidationdate() {
        return validationdate;
    }

    public void setValidationdate(Date validationdate) {
        this.validationdate = validationdate;
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public BigDecimal getPostalcharges() {
        return postalcharges;
    }

    public void setPostalcharges(BigDecimal postalcharges) {
        this.postalcharges = postalcharges;
    }

    @XmlTransient
    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    public Address getDelAddress() {
        return delAddress;
    }

    public void setDelAddress(Address delAddress) {
        this.delAddress = delAddress;
    }

    public Customer getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Customer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public Deliverymode getIddeliverymode() {
        return iddeliverymode;
    }

    public void setIddeliverymode(Deliverymode iddeliverymode) {
        this.iddeliverymode = iddeliverymode;
    }

    @XmlTransient
    public Collection<LineOrder> getLineOrderCollection() {
        return lineOrderCollection;
    }

    public void setLineOrderCollection(Collection<LineOrder> lineOrderCollection) {
        this.lineOrderCollection = lineOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numorder != null ? numorder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderTable)) {
            return false;
        }
        OrderTable other = (OrderTable) object;
        if ((this.numorder == null && other.numorder != null) || (this.numorder != null && !this.numorder.equals(other.numorder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.OrderTable[ numorder=" + numorder + " ]";
    }
    
}
