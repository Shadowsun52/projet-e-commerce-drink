/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author Alexandre
 */
public class Category {
    private Integer id;
    private boolean dateRequired;
    private HashMap<String, String> hashLabel;

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
    public HashMap<String, String> getHashLabel() {
        return hashLabel;
    }

    /**
     * @param hashLabel the hashLabel to set
     */
    public void setHashLabel(HashMap<String, String> hashLabel) {
        this.hashLabel = hashLabel;
    }
    
    public void addLabel(String language, String label){
        this.hashLabel.put(language, label);
    }
    
    public String getLabel(String language) {
        return hashLabel.get(language);
    }
}
