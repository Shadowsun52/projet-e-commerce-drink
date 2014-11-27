/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Alexandre
 */
public class Language implements Serializable{
    private Integer id;
    private String label;
    private String slogan;
    private String shortLabel;

    public Language() {
    }

    public Language(Integer id, String label, String slogan, 
            String shortLabel) {
        this.id = id;
        this.label = label;
        this.slogan = slogan;
        this.shortLabel = shortLabel;
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
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the slogan
     */
    public String getSlogan() {
        return slogan;
    }

    /**
     * @param slogan the slogan to set
     */
    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    /**
     * @return the shortLabel
     */
    public String getShortLabel() {
        return shortLabel;
    }

    /**
     * @param shortLabel the shortLabel to set
     */
    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }
    
    @Override
    public int hashCode() {        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        return !((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Language[ id=" + id + " ]";
    }
}
