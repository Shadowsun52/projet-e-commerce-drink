/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "LOCALITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Locality.findAll", query = "SELECT l FROM Locality l"),
    @NamedQuery(name = "Locality.findByIdlocality", query = "SELECT l FROM Locality l WHERE l.idlocality = :idlocality"),
    @NamedQuery(name = "Locality.findByPostcode", query = "SELECT l FROM Locality l WHERE l.postcode = :postcode")})
public class Locality implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idlocality")
    private Collection<Customer> customerCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLOCALITY")
    private Integer idlocality;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSTCODE")
    private int postcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locality")
    private Collection<LangLoc> langLocCollection;
    @JoinColumn(name = "IDCOUNTRY", referencedColumnName = "IDCOUNTRY")
    @ManyToOne(optional = false)
    private Country idcountry;

    public Locality() {
    }

    public Locality(Integer idlocality) {
        this.idlocality = idlocality;
    }

    public Locality(Integer idlocality, int postcode) {
        this.idlocality = idlocality;
        this.postcode = postcode;
    }

    public Integer getIdlocality() {
        return idlocality;
    }

    public void setIdlocality(Integer idlocality) {
        this.idlocality = idlocality;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @XmlTransient
    public Collection<LangLoc> getLangLocCollection() {
        return langLocCollection;
    }

    public void setLangLocCollection(Collection<LangLoc> langLocCollection) {
        this.langLocCollection = langLocCollection;
    }

    public Country getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(Country idcountry) {
        this.idcountry = idcountry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlocality != null ? idlocality.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Locality)) {
            return false;
        }
        Locality other = (Locality) object;
        if ((this.idlocality == null && other.idlocality != null) || (this.idlocality != null && !this.idlocality.equals(other.idlocality))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Locality[ idlocality=" + idlocality + " ]";
    }
    
    public String getLabel(String language) {
        for(LangLoc langLoc : langLocCollection) {
            if(langLoc.getLanguage().getShortlabel().equals(language))
                return langLoc.getLabel();
        }
        //il faudra gérer une erreur ici
        return "not found";
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }
}
