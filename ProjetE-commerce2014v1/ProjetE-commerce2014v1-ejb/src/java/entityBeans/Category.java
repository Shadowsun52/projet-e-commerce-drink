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
@Table(name = "CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByIdcategory", query = "SELECT c FROM Category c WHERE c.idcategory = :idcategory"),
    @NamedQuery(name = "Category.findByDaterequired", query = "SELECT c FROM Category c WHERE c.daterequired = :daterequired")})
public class Category implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEREQUIRED")
    private Boolean daterequired;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCATEGORY")
    private Integer idcategory;
    @OneToMany(mappedBy = "idcategory")
    private Collection<Promotion> promotionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Collection<LangCat> langCatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategory")
    private Collection<Type> typeCollection;

    public Category() {
    }

    public Category(Integer idcategory) {
        this.idcategory = idcategory;
    }

    public Category(Integer idcategory, boolean daterequired) {
        this.idcategory = idcategory;
        this.daterequired = daterequired;
    }

    public Integer getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Integer idcategory) {
        this.idcategory = idcategory;
    }


    @XmlTransient
    public Collection<Promotion> getPromotionCollection() {
        return promotionCollection;
    }

    public void setPromotionCollection(Collection<Promotion> promotionCollection) {
        this.promotionCollection = promotionCollection;
    }

    @XmlTransient
    public Collection<LangCat> getLangCatCollection() {
        return langCatCollection;
    }

    public void setLangCatCollection(Collection<LangCat> langCatCollection) {
        this.langCatCollection = langCatCollection;
    }

    @XmlTransient
    public Collection<Type> getTypeCollection() {
        return typeCollection;
    }

    public void setTypeCollection(Collection<Type> typeCollection) {
        this.typeCollection = typeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategory != null ? idcategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.idcategory == null && other.idcategory != null) || (this.idcategory != null && !this.idcategory.equals(other.idcategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Category[ idcategory=" + idcategory + " ]";
    }
    public String getLabel(String language) {
        for (LangCat langCat : langCatCollection) {
            if(langCat.getLanguage().getShortlabel().equals(language))
                return langCat.getLabel();
        }
        //il faudra gérer une erreur ici
        return "not found";
    }

    public Boolean getDaterequired() {
        return daterequired;
    }

    public void setDaterequired(Boolean daterequired) {
        this.daterequired = daterequired;
    }
}
