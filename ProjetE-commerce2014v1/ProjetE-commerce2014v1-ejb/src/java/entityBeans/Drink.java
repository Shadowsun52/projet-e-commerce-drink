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
@Table(name = "DRINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drink.findAll", query = "SELECT d FROM Drink d"),
    @NamedQuery(name = "Drink.findByIddrink", query = "SELECT d FROM Drink d WHERE d.iddrink = :iddrink"),
    @NamedQuery(name = "Drink.findByCurrentprice", query = "SELECT d FROM Drink d WHERE d.currentprice = :currentprice"),
    @NamedQuery(name = "Drink.findByCapacity", query = "SELECT d FROM Drink d WHERE d.capacity = :capacity"),
    @NamedQuery(name = "Drink.findByPercentagealcohol", query = "SELECT d FROM Drink d WHERE d.percentagealcohol = :percentagealcohol"),
    @NamedQuery(name = "Drink.findByDatebottling", query = "SELECT d FROM Drink d WHERE d.datebottling = :datebottling")})
public class Drink implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDDRINK")
    private Integer iddrink;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "CURRENTPRICE")
    private BigDecimal currentprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CAPACITY")
    private BigDecimal capacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERCENTAGEALCOHOL")
    private short percentagealcohol;
    @Column(name = "DATEBOTTLING")
    @Temporal(TemporalType.DATE)
    private Date datebottling;
    @JoinTable(name = "TYPEDRINK", joinColumns = {
        @JoinColumn(name = "IDDRINK", referencedColumnName = "IDDRINK")}, inverseJoinColumns = {
        @JoinColumn(name = "IDTYPE", referencedColumnName = "IDTYPE")})
    @ManyToMany
    private Collection<Type> typeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drink")
    private Collection<LangDrink> langDrinkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drink")
    private Collection<LineOrder> lineOrderCollection;
    @OneToMany(mappedBy = "iddrink")
    private Collection<Promotion> promotionCollection;

    public Drink() {
    }

    public Drink(Integer iddrink) {
        this.iddrink = iddrink;
    }

    public Drink(Integer iddrink, BigDecimal currentprice, BigDecimal capacity, short percentagealcohol) {
        this.iddrink = iddrink;
        this.currentprice = currentprice;
        this.capacity = capacity;
        this.percentagealcohol = percentagealcohol;
    }

    public Integer getIddrink() {
        return iddrink;
    }

    public void setIddrink(Integer iddrink) {
        this.iddrink = iddrink;
    }

    public BigDecimal getCurrentprice() {
        return currentprice;
    }

    public void setCurrentprice(BigDecimal currentprice) {
        this.currentprice = currentprice;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public short getPercentagealcohol() {
        return percentagealcohol;
    }

    public void setPercentagealcohol(short percentagealcohol) {
        this.percentagealcohol = percentagealcohol;
    }

    public Date getDatebottling() {
        return datebottling;
    }

    public void setDatebottling(Date datebottling) {
        this.datebottling = datebottling;
    }

    @XmlTransient
    public Collection<Type> getTypeCollection() {
        return typeCollection;
    }

    public void setTypeCollection(Collection<Type> typeCollection) {
        this.typeCollection = typeCollection;
    }

    @XmlTransient
    public Collection<LangDrink> getLangDrinkCollection() {
        return langDrinkCollection;
    }

    public void setLangDrinkCollection(Collection<LangDrink> langDrinkCollection) {
        this.langDrinkCollection = langDrinkCollection;
    }

    @XmlTransient
    public Collection<LineOrder> getLineOrderCollection() {
        return lineOrderCollection;
    }

    public void setLineOrderCollection(Collection<LineOrder> lineOrderCollection) {
        this.lineOrderCollection = lineOrderCollection;
    }

    @XmlTransient
    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddrink != null ? iddrink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drink)) {
            return false;
        }
        Drink other = (Drink) object;
        if ((this.iddrink == null && other.iddrink != null) || (this.iddrink != null && !this.iddrink.equals(other.iddrink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Drink[ iddrink=" + iddrink + " ]";
    }
    
}
