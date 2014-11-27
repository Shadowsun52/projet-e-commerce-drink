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
 * @author Alexandre
 */
public class Country implements Serializable{
    private Integer id;
    private short tva;
    private HashMap<Language,String> hashLabel;
    
    public Country() {  
    }

    public Country(Integer id, short tva) {
        this.id = id;
        this.tva = tva;
        hashLabel = new HashMap();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the tva
     */
    public short getTva() {
        return tva;
    }

    /**
     * @param tva the tva to set
     */
    public void setTva(short tva) {
        this.tva = tva;
    }

    /**
     * @return the hashLabel
     */
    public HashMap<Language,String> getHashLabel() {
        return hashLabel;
    }

    /**
     * @param hashLabel the hashLabel to set
     */
    public void setHashLabel(HashMap<Language,String> hashLabel) {
        this.hashLabel = hashLabel;
    }
    
    public void addLabel(Language language, String label) {
        this.hashLabel.put(language, label);
    }
    
    public String getLabel(Language language) {
        return hashLabel.get(language);
    }
    
    @Override
    public int hashCode() {        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        return !((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Person[ id=" + id + " ]";
    }
}
