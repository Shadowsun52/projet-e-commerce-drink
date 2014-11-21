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
@Table(name = "LANG_DRINK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangDrink.findAll", query = "SELECT l FROM LangDrink l"),
    @NamedQuery(name = "LangDrink.findByIddrink", query = "SELECT l FROM LangDrink l WHERE l.langDrinkPK.iddrink = :iddrink"),
    @NamedQuery(name = "LangDrink.findByIdlanguage", query = "SELECT l FROM LangDrink l WHERE l.langDrinkPK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangDrink.findByLabel", query = "SELECT l FROM LangDrink l WHERE l.label = :label"),
    @NamedQuery(name = "LangDrink.findByDescription", query = "SELECT l FROM LangDrink l WHERE l.description = :description")})
public class LangDrink implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangDrinkPK langDrinkPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "LABEL")
    private String label;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "IDDRINK", referencedColumnName = "IDDRINK", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Drink drink;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;

    public LangDrink() {
    }

    public LangDrink(LangDrinkPK langDrinkPK) {
        this.langDrinkPK = langDrinkPK;
    }

    public LangDrink(LangDrinkPK langDrinkPK, String label, String description) {
        this.langDrinkPK = langDrinkPK;
        this.label = label;
        this.description = description;
    }

    public LangDrink(int iddrink, int idlanguage) {
        this.langDrinkPK = new LangDrinkPK(iddrink, idlanguage);
    }

    public LangDrinkPK getLangDrinkPK() {
        return langDrinkPK;
    }

    public void setLangDrinkPK(LangDrinkPK langDrinkPK) {
        this.langDrinkPK = langDrinkPK;
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

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
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
        hash += (langDrinkPK != null ? langDrinkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangDrink)) {
            return false;
        }
        LangDrink other = (LangDrink) object;
        if ((this.langDrinkPK == null && other.langDrinkPK != null) || (this.langDrinkPK != null && !this.langDrinkPK.equals(other.langDrinkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangDrink[ langDrinkPK=" + langDrinkPK + " ]";
    }
    
}
