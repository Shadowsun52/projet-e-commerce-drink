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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "LANGUAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l"),
    @NamedQuery(name = "Language.findByIdlanguage", query = "SELECT l FROM Language l WHERE l.idlanguage = :idlanguage"),
    @NamedQuery(name = "Language.findByLabel", query = "SELECT l FROM Language l WHERE l.label = :label"),
    @NamedQuery(name = "Language.findBySlogan", query = "SELECT l FROM Language l WHERE l.slogan = :slogan"),
    @NamedQuery(name = "Language.findByShortlabel", query = "SELECT l FROM Language l WHERE l.shortlabel = :shortlabel")})
public class Language implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private Collection<LangLoc> langLocCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private Collection<LangCountry> langCountryCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLANGUAGE")
    private Integer idlanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LABEL")
    private String label;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SLOGAN")
    private String slogan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "SHORTLABEL")
    private String shortlabel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language")
    private Collection<LangCat> langCatCollection;

    public Language() {
    }

    public Language(Integer idlanguage) {
        this.idlanguage = idlanguage;
    }

    public Language(Integer idlanguage, String label, String slogan, String shortlabel) {
        this.idlanguage = idlanguage;
        this.label = label;
        this.slogan = slogan;
        this.shortlabel = shortlabel;
    }

    public Integer getIdlanguage() {
        return idlanguage;
    }

    public void setIdlanguage(Integer idlanguage) {
        this.idlanguage = idlanguage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getShortlabel() {
        return shortlabel;
    }

    public void setShortlabel(String shortlabel) {
        this.shortlabel = shortlabel;
    }

    @XmlTransient
    public Collection<LangCat> getLangCatCollection() {
        return langCatCollection;
    }

    public void setLangCatCollection(Collection<LangCat> langCatCollection) {
        this.langCatCollection = langCatCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlanguage != null ? idlanguage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.idlanguage == null && other.idlanguage != null) || (this.idlanguage != null && !this.idlanguage.equals(other.idlanguage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Language[ idlanguage=" + idlanguage + " ]";
    }

    @XmlTransient
    public Collection<LangLoc> getLangLocCollection() {
        return langLocCollection;
    }

    public void setLangLocCollection(Collection<LangLoc> langLocCollection) {
        this.langLocCollection = langLocCollection;
    }

    @XmlTransient
    public Collection<LangCountry> getLangCountryCollection() {
        return langCountryCollection;
    }

    public void setLangCountryCollection(Collection<LangCountry> langCountryCollection) {
        this.langCountryCollection = langCountryCollection;
    }
    
}