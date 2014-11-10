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
@Table(name = "COUNTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByIdcountry", query = "SELECT c FROM Country c WHERE c.idcountry = :idcountry"),
    @NamedQuery(name = "Country.findByTva", query = "SELECT c FROM Country c WHERE c.tva = :tva")})
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCOUNTRY")
    private Integer idcountry;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TVA")
    private short tva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcountry")
    private Collection<Locality> localityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Collection<LangCountry> langCountryCollection;

    public Country() {
    }

    public Country(Integer idcountry) {
        this.idcountry = idcountry;
    }

    public Country(Integer idcountry, short tva) {
        this.idcountry = idcountry;
        this.tva = tva;
    }

    public Integer getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(Integer idcountry) {
        this.idcountry = idcountry;
    }

    public short getTva() {
        return tva;
    }

    public void setTva(short tva) {
        this.tva = tva;
    }

    @XmlTransient
    public Collection<Locality> getLocalityCollection() {
        return localityCollection;
    }

    public void setLocalityCollection(Collection<Locality> localityCollection) {
        this.localityCollection = localityCollection;
    }

    @XmlTransient
    public Collection<LangCountry> getLangCountryCollection() {
        return langCountryCollection;
    }

    public void setLangCountryCollection(Collection<LangCountry> langCountryCollection) {
        this.langCountryCollection = langCountryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcountry != null ? idcountry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.idcountry == null && other.idcountry != null) || (this.idcountry != null && !this.idcountry.equals(other.idcountry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Country[ idcountry=" + idcountry + " ]";
    }
    
    public String getLabel(String language) {
        for (LangCountry langCountry : langCountryCollection) {
            if(langCountry.getLanguage().getShortlabel().equals(language))
                return langCountry.getLabel();
        }
        //il faudra gérer une erreur ici
        return "not found";
    }
}
