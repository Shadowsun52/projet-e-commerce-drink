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
public class Category implements Serializable{
    private Integer id;
    private boolean dateRequired;
    private HashMap<Language, String> hashLabel;

    public Category() {
    }

    public Category(Integer id, boolean dateRequired) {
        this.id = id;
        this.dateRequired = dateRequired;
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
     * @return the dateRequired
     */
    public boolean isDateRequired() {
        return dateRequired;
    }

    /**
     * @param dateRequired the dateRequired to set
     */
    public void setDateRequired(boolean dateRequired) {
        this.dateRequired = dateRequired;
    }

    /**
     * @return the hashLabel
     */
    public HashMap<Language, String> getHashLabel() {
        return hashLabel;
    }

    /**
     * @param hashLabel the hashLabel to set
     */
    public void setHashLabel(HashMap<Language, String> hashLabel) {
        this.hashLabel = hashLabel;
    }
    
    public void addLabel(Language language, String label){
        this.hashLabel.put(language, label);
    }
    
    public String getLabel(Language language) {
        return hashLabel.get(language);
    }
}
