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
public class Country {
    private Integer id;
    private Integer tva;
    private HashMap<String,String> hashLabel;
    
    public Country() {  
    }

    public Country(Integer id, Integer tva) {
        this.id = id;
        this.tva = tva;
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
    public Integer getTva() {
        return tva;
    }

    /**
     * @param tva the tva to set
     */
    public void setTva(Integer tva) {
        this.tva = tva;
    }

    /**
     * @return the hashLabel
     */
    public HashMap<String,String> getHashLabel() {
        return hashLabel;
    }

    /**
     * @param hashLabel the hashLabel to set
     */
    public void setHashLabel(HashMap<String,String> hashLabel) {
        this.hashLabel = hashLabel;
    }
    
    public void addLabel(String language, String label) {
        this.hashLabel.put(language, label);
    }
}
