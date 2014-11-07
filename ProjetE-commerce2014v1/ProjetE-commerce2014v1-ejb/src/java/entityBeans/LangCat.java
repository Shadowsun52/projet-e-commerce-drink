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
@Table(name = "LANG_CAT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangCat.findAll", query = "SELECT l FROM LangCat l"),
    @NamedQuery(name = "LangCat.findByIdcategory", query = "SELECT l FROM LangCat l WHERE l.langCatPK.idcategory = :idcategory"),
    @NamedQuery(name = "LangCat.findByIdlanguage", query = "SELECT l FROM LangCat l WHERE l.langCatPK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangCat.findByLabel", query = "SELECT l FROM LangCat l WHERE l.label = :label")})
public class LangCat implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangCatPK langCatPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LABEL")
    private String label;
    @JoinColumn(name = "IDCATEGORY", referencedColumnName = "IDCATEGORY", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;

    public LangCat() {
    }

    public LangCat(LangCatPK langCatPK) {
        this.langCatPK = langCatPK;
    }

    public LangCat(LangCatPK langCatPK, String label) {
        this.langCatPK = langCatPK;
        this.label = label;
    }

    public LangCat(int idcategory, int idlanguage) {
        this.langCatPK = new LangCatPK(idcategory, idlanguage);
    }

    public LangCatPK getLangCatPK() {
        return langCatPK;
    }

    public void setLangCatPK(LangCatPK langCatPK) {
        this.langCatPK = langCatPK;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        hash += (langCatPK != null ? langCatPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangCat)) {
            return false;
        }
        LangCat other = (LangCat) object;
        if ((this.langCatPK == null && other.langCatPK != null) || (this.langCatPK != null && !this.langCatPK.equals(other.langCatPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangCat[ langCatPK=" + langCatPK + " ]";
    }
    
}
