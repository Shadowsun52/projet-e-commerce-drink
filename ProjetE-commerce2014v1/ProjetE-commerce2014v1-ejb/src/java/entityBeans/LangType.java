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
@Table(name = "LANG_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangType.findAll", query = "SELECT l FROM LangType l"),
    @NamedQuery(name = "LangType.findByIdlanguage", query = "SELECT l FROM LangType l WHERE l.langTypePK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangType.findByIdtype", query = "SELECT l FROM LangType l WHERE l.langTypePK.idtype = :idtype"),
    @NamedQuery(name = "LangType.findByLabel", query = "SELECT l FROM LangType l WHERE l.label = :label")})
public class LangType implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangTypePK langTypePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LABEL")
    private String label;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "IDTYPE", referencedColumnName = "IDTYPE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Type type;

    public LangType() {
    }

    public LangType(LangTypePK langTypePK) {
        this.langTypePK = langTypePK;
    }

    public LangType(LangTypePK langTypePK, String label) {
        this.langTypePK = langTypePK;
        this.label = label;
    }

    public LangType(int idlanguage, int idtype) {
        this.langTypePK = new LangTypePK(idlanguage, idtype);
    }

    public LangTypePK getLangTypePK() {
        return langTypePK;
    }

    public void setLangTypePK(LangTypePK langTypePK) {
        this.langTypePK = langTypePK;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langTypePK != null ? langTypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangType)) {
            return false;
        }
        LangType other = (LangType) object;
        if ((this.langTypePK == null && other.langTypePK != null) || (this.langTypePK != null && !this.langTypePK.equals(other.langTypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangType[ langTypePK=" + langTypePK + " ]";
    }
    
}
