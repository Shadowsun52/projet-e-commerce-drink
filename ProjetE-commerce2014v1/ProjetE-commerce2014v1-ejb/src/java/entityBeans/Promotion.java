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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "PROMOTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promotion.findAll", query = "SELECT p FROM Promotion p"),
    @NamedQuery(name = "Promotion.findByIdpromotion", query = "SELECT p FROM Promotion p WHERE p.idpromotion = :idpromotion"),
    @NamedQuery(name = "Promotion.findByDatestart", query = "SELECT p FROM Promotion p WHERE p.datestart = :datestart"),
    @NamedQuery(name = "Promotion.findByDateend", query = "SELECT p FROM Promotion p WHERE p.dateend = :dateend"),
    @NamedQuery(name = "Promotion.findByCodepromo", query = "SELECT p FROM Promotion p WHERE p.codepromo = :codepromo"),
    @NamedQuery(name = "Promotion.findByPromoUnique", query = "SELECT p FROM Promotion p WHERE p.promoUnique = :promoUnique"),
    @NamedQuery(name = "Promotion.findByTypediscount", query = "SELECT p FROM Promotion p WHERE p.typediscount = :typediscount"),
    @NamedQuery(name = "Promotion.findByPercentagediscount", query = "SELECT p FROM Promotion p WHERE p.percentagediscount = :percentagediscount"),
    @NamedQuery(name = "Promotion.findByAmountdiscount", query = "SELECT p FROM Promotion p WHERE p.amountdiscount = :amountdiscount"),
    @NamedQuery(name = "Promotion.findByMinquantity", query = "SELECT p FROM Promotion p WHERE p.minquantity = :minquantity")})
public class Promotion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPROMOTION")
    private Integer idpromotion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATESTART")
    @Temporal(TemporalType.DATE)
    private Date datestart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEEND")
    @Temporal(TemporalType.DATE)
    private Date dateend;
    @Size(max = 30)
    @Column(name = "CODEPROMO")
    private String codepromo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROMO_UNIQUE")
    private Character promoUnique;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPEDISCOUNT")
    private Character typediscount;
    @Column(name = "PERCENTAGEDISCOUNT")
    private Short percentagediscount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNTDISCOUNT")
    private BigDecimal amountdiscount;
    @Column(name = "MINQUANTITY")
    private Short minquantity;
    @ManyToMany(mappedBy = "promotionCollection")
    private Collection<OrderTable> orderTableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotion")
    private Collection<LangPromotion> langPromotionCollection;
    @JoinColumn(name = "IDCATEGORY", referencedColumnName = "IDCATEGORY")
    @ManyToOne
    private Category idcategory;
    @JoinColumn(name = "IDDRINK", referencedColumnName = "IDDRINK")
    @ManyToOne
    private Drink iddrink;

    public Promotion() {
    }

    public Promotion(Integer idpromotion) {
        this.idpromotion = idpromotion;
    }

    public Promotion(Integer idpromotion, Date datestart, Date dateend, Character promoUnique, Character typediscount) {
        this.idpromotion = idpromotion;
        this.datestart = datestart;
        this.dateend = dateend;
        this.promoUnique = promoUnique;
        this.typediscount = typediscount;
    }

    public Integer getIdpromotion() {
        return idpromotion;
    }

    public void setIdpromotion(Integer idpromotion) {
        this.idpromotion = idpromotion;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public String getCodepromo() {
        return codepromo;
    }

    public void setCodepromo(String codepromo) {
        this.codepromo = codepromo;
    }

    public Character getPromoUnique() {
        return promoUnique;
    }

    public void setPromoUnique(Character promoUnique) {
        this.promoUnique = promoUnique;
    }

    public Character getTypediscount() {
        return typediscount;
    }

    public void setTypediscount(Character typediscount) {
        this.typediscount = typediscount;
    }

    public Short getPercentagediscount() {
        return percentagediscount;
    }

    public void setPercentagediscount(Short percentagediscount) {
        this.percentagediscount = percentagediscount;
    }

    public BigDecimal getAmountdiscount() {
        return amountdiscount;
    }

    public void setAmountdiscount(BigDecimal amountdiscount) {
        this.amountdiscount = amountdiscount;
    }

    public Short getMinquantity() {
        return minquantity;
    }

    public void setMinquantity(Short minquantity) {
        this.minquantity = minquantity;
    }

    @XmlTransient
    public Collection<OrderTable> getOrderTableCollection() {
        return orderTableCollection;
    }

    public void setOrderTableCollection(Collection<OrderTable> orderTableCollection) {
        this.orderTableCollection = orderTableCollection;
    }

    @XmlTransient
    public Collection<LangPromotion> getLangPromotionCollection() {
        return langPromotionCollection;
    }

    public void setLangPromotionCollection(Collection<LangPromotion> langPromotionCollection) {
        this.langPromotionCollection = langPromotionCollection;
    }

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

    public Drink getIddrink() {
        return iddrink;
    }

    public void setIddrink(Drink iddrink) {
        this.iddrink = iddrink;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpromotion != null ? idpromotion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.idpromotion == null && other.idpromotion != null) || (this.idpromotion != null && !this.idpromotion.equals(other.idpromotion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Promotion[ idpromotion=" + idpromotion + " ]";
    }
    
}
