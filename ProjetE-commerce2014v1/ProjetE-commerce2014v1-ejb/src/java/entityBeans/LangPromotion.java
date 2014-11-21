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
@Table(name = "LANG_PROMOTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LangPromotion.findAll", query = "SELECT l FROM LangPromotion l"),
    @NamedQuery(name = "LangPromotion.findByIdlanguage", query = "SELECT l FROM LangPromotion l WHERE l.langPromotionPK.idlanguage = :idlanguage"),
    @NamedQuery(name = "LangPromotion.findByIdpromotion", query = "SELECT l FROM LangPromotion l WHERE l.langPromotionPK.idpromotion = :idpromotion"),
    @NamedQuery(name = "LangPromotion.findByMessage", query = "SELECT l FROM LangPromotion l WHERE l.message = :message")})
public class LangPromotion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LangPromotionPK langPromotionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "MESSAGE")
    private String message;
    @JoinColumn(name = "IDLANGUAGE", referencedColumnName = "IDLANGUAGE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "IDPROMOTION", referencedColumnName = "IDPROMOTION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Promotion promotion;

    public LangPromotion() {
    }

    public LangPromotion(LangPromotionPK langPromotionPK) {
        this.langPromotionPK = langPromotionPK;
    }

    public LangPromotion(LangPromotionPK langPromotionPK, String message) {
        this.langPromotionPK = langPromotionPK;
        this.message = message;
    }

    public LangPromotion(int idlanguage, int idpromotion) {
        this.langPromotionPK = new LangPromotionPK(idlanguage, idpromotion);
    }

    public LangPromotionPK getLangPromotionPK() {
        return langPromotionPK;
    }

    public void setLangPromotionPK(LangPromotionPK langPromotionPK) {
        this.langPromotionPK = langPromotionPK;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (langPromotionPK != null ? langPromotionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LangPromotion)) {
            return false;
        }
        LangPromotion other = (LangPromotion) object;
        if ((this.langPromotionPK == null && other.langPromotionPK != null) || (this.langPromotionPK != null && !this.langPromotionPK.equals(other.langPromotionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.LangPromotion[ langPromotionPK=" + langPromotionPK + " ]";
    }
    
}
