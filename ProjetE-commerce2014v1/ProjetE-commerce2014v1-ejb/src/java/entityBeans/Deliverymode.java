/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "DELIVERYMODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deliverymode.findAll", query = "SELECT d FROM Deliverymode d"),
    @NamedQuery(name = "Deliverymode.findByIddeliverymode", query = "SELECT d FROM Deliverymode d WHERE d.iddeliverymode = :iddeliverymode"),
    @NamedQuery(name = "Deliverymode.findByCurrentpostalcharges", query = "SELECT d FROM Deliverymode d WHERE d.currentpostalcharges = :currentpostalcharges")})
public class Deliverymode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDELIVERYMODE")
    private Integer iddeliverymode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURRENTPOSTALCHARGES")
    private BigDecimal currentpostalcharges;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddeliverymode")
    private Collection<OrderTable> orderTableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deliverymode")
    private Collection<LangDelmode> langDelmodeCollection;

    public Deliverymode() {
    }

    public Deliverymode(Integer iddeliverymode) {
        this.iddeliverymode = iddeliverymode;
    }

    public Deliverymode(Integer iddeliverymode, BigDecimal currentpostalcharges) {
        this.iddeliverymode = iddeliverymode;
        this.currentpostalcharges = currentpostalcharges;
    }

    public Integer getIddeliverymode() {
        return iddeliverymode;
    }

    public void setIddeliverymode(Integer iddeliverymode) {
        this.iddeliverymode = iddeliverymode;
    }

    public BigDecimal getCurrentpostalcharges() {
        return currentpostalcharges;
    }

    public void setCurrentpostalcharges(BigDecimal currentpostalcharges) {
        this.currentpostalcharges = currentpostalcharges;
    }

    @XmlTransient
    public Collection<OrderTable> getOrderTableCollection() {
        return orderTableCollection;
    }

    public void setOrderTableCollection(Collection<OrderTable> orderTableCollection) {
        this.orderTableCollection = orderTableCollection;
    }

    @XmlTransient
    public Collection<LangDelmode> getLangDelmodeCollection() {
        return langDelmodeCollection;
    }

    public void setLangDelmodeCollection(Collection<LangDelmode> langDelmodeCollection) {
        this.langDelmodeCollection = langDelmodeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeliverymode != null ? iddeliverymode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deliverymode)) {
            return false;
        }
        Deliverymode other = (Deliverymode) object;
        if ((this.iddeliverymode == null && other.iddeliverymode != null) || (this.iddeliverymode != null && !this.iddeliverymode.equals(other.iddeliverymode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Deliverymode[ iddeliverymode=" + iddeliverymode + " ]";
    }
    
}
