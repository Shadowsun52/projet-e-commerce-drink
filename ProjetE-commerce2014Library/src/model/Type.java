/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Alex
 */
public class Type implements Serializable {
    private Integer id;
    private Category category;
    private HashMap<Language,String> hashLabel;

    public Type() {
    }

    public Type(Integer id, Category category) {
        this.id = id;
        this.category = category;
        this.hashLabel=new HashMap<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void setHashLabel(HashMap<Language,String> hashLabel) {
        this.hashLabel = hashLabel;
    }
    
    public void addLabel(Language language, String label){
        this.hashLabel.put(language, label);
    }
    
    public String getLabel(Language language) {
        return hashLabel.get(language);
    }
    
    @Override
    public int hashCode() {    	
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Type)) {
                return false;
        }
        Type other = (Type) object;
        return !((this.id == null && other.id != null) ||
            (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Type[ id=" + id + " ]";
    }
}
