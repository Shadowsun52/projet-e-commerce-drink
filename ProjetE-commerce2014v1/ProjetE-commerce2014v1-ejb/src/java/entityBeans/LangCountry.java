/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "LANG_COUNTRY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangCountry.findAll", query = "SELECT l FROM LangCountry l"),
    @NamedQuery(name = "LangCountry.findByIdcountry", query = "SELECT l FROM LangCountry l WHERE l.langCountryPK.idcountry = :idcountry"),
    @NamedQuery(name = "LangCountry.findByIdlanguage", query = "SELECT l FROM LangCountry l WHERE l.langCountryPK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangCountry.findByLabel", query = "SELECT l FROM LangCountry l WHERE l.label = :label")})
public class LangCountry implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangCountryPK langCountryPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LABEL")
    private String label;
    @JoinColumn(name = "IDCOUNTRY", referencedColumnName = "IDCOUNTRY", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Country country;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;

    public LangCountry() {
    }

    public LangCountry(LangCountryPK langCountryPK) {
        this.langCountryPK = langCountryPK;
    }

    public LangCountry(LangCountryPK langCountryPK, String label) {
        this.langCountryPK = langCountryPK;
        this.label = label;
    }

    public LangCountry(int idcountry, int idlanguage) {
        this.langCountryPK = new LangCountryPK(idcountry, idlanguage);
    }

    public LangCountryPK getLangCountryPK() {
        return langCountryPK;
    }

    public void setLangCountryPK(LangCountryPK langCountryPK) {
        this.langCountryPK = langCountryPK;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langCountryPK != null ? langCountryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangCountry)) {
            return false;
        }
        LangCountry other = (LangCountry) object;
        if ((this.langCountryPK == null && other.langCountryPK != null) || (this.langCountryPK != null && !this.langCountryPK.equals(other.langCountryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangCountry[ langCountryPK=" + langCountryPK + " ]";
    }
    
}
