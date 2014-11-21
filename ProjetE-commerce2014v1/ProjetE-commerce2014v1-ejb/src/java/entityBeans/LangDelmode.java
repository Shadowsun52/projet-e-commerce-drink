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
@Table(name = "LANG_DELMODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangDelmode.findAll", query = "SELECT l FROM LangDelmode l"),
    @NamedQuery(name = "LangDelmode.findByIddeliverymode", query = "SELECT l FROM LangDelmode l WHERE l.langDelmodePK.iddeliverymode = :iddeliverymode"),
    @NamedQuery(name = "LangDelmode.findByIdlanguage", query = "SELECT l FROM LangDelmode l WHERE l.langDelmodePK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangDelmode.findByLabel", query = "SELECT l FROM LangDelmode l WHERE l.label = :label"),
    @NamedQuery(name = "LangDelmode.findByDescription", query = "SELECT l FROM LangDelmode l WHERE l.description = :description")})
public class LangDelmode implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangDelmodePK langDelmodePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LABEL")
    private String label;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "IDDELIVERYMODE", referencedColumnName = "IDDELIVERYMODE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Deliverymode deliverymode;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;

    public LangDelmode() {
    }

    public LangDelmode(LangDelmodePK langDelmodePK) {
        this.langDelmodePK = langDelmodePK;
    }

    public LangDelmode(LangDelmodePK langDelmodePK, String label, String description) {
        this.langDelmodePK = langDelmodePK;
        this.label = label;
        this.description = description;
    }

    public LangDelmode(int iddeliverymode, int idlanguage) {
        this.langDelmodePK = new LangDelmodePK(iddeliverymode, idlanguage);
    }

    public LangDelmodePK getLangDelmodePK() {
        return langDelmodePK;
    }

    public void setLangDelmodePK(LangDelmodePK langDelmodePK) {
        this.langDelmodePK = langDelmodePK;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Deliverymode getDeliverymode() {
        return deliverymode;
    }

    public void setDeliverymode(Deliverymode deliverymode) {
        this.deliverymode = deliverymode;
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
        hash += (langDelmodePK != null ? langDelmodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangDelmode)) {
            return false;
        }
        LangDelmode other = (LangDelmode) object;
        if ((this.langDelmodePK == null && other.langDelmodePK != null) || (this.langDelmodePK != null && !this.langDelmodePK.equals(other.langDelmodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangDelmode[ langDelmodePK=" + langDelmodePK + " ]";
    }
    
}
