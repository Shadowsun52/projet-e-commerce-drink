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
@Table(name = "LANG_LOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangLoc.findAll", query = "SELECT l FROM LangLoc l"),
    @NamedQuery(name = "LangLoc.findByIdlanguage", query = "SELECT l FROM LangLoc l WHERE l.langLocPK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangLoc.findByIdlocality", query = "SELECT l FROM LangLoc l WHERE l.langLocPK.idlocality = :idlocality"),
    @NamedQuery(name = "LangLoc.findByLabel", query = "SELECT l FROM LangLoc l WHERE l.label = :label")})
public class LangLoc implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangLocPK langLocPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LABEL")
    private String label;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "IDLOCALITY", referencedColumnName = "IDLOCALITY", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Locality locality;

    public LangLoc() {
    }

    public LangLoc(LangLocPK langLocPK) {
        this.langLocPK = langLocPK;
    }

    public LangLoc(LangLocPK langLocPK, String label) {
        this.langLocPK = langLocPK;
        this.label = label;
    }

    public LangLoc(int idlanguage, int idlocality) {
        this.langLocPK = new LangLocPK(idlanguage, idlocality);
    }

    public LangLocPK getLangLocPK() {
        return langLocPK;
    }

    public void setLangLocPK(LangLocPK langLocPK) {
        this.langLocPK = langLocPK;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langLocPK != null ? langLocPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangLoc)) {
            return false;
        }
        LangLoc other = (LangLoc) object;
        if ((this.langLocPK == null && other.langLocPK != null) || (this.langLocPK != null && !this.langLocPK.equals(other.langLocPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangLoc[ langLocPK=" + langLocPK + " ]";
    }
    
}
