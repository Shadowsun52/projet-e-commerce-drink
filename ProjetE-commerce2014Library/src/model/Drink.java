/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Alex
 */
public class Drink {
    private int id;
    private BigDecimal currentPrice;
    private BigDecimal capacity;
    private short percentageAlcohol;
    private Date dateBottling;
    private HashMap<Language,String> hashLabel;

    public Drink() {
    }

    public Drink(int id, BigDecimal currentPrice, BigDecimal capacity, short percentageAlcohol, Date dateBottling) {
        this.id = id;
        this.currentPrice = currentPrice;
        this.capacity = capacity;
        this.percentageAlcohol = percentageAlcohol;
        this.dateBottling = dateBottling;
        this.hashLabel=new HashMap<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the currentPrice
     */
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    /**
     * @param currentPrice the currentPrice to set
     */
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    /**
     * @return the capacity
     */
    public BigDecimal getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the percentageAlcohol
     */
    public short getPercentageAlcohol() {
        return percentageAlcohol;
    }

    /**
     * @param percentageAlcohol the percentageAlcohol to set
     */
    public void setPercentageAlcohol(short percentageAlcohol) {
        this.percentageAlcohol = percentageAlcohol;
    }

    /**
     * @return the dateBottling
     */
    public Date getDateBottling() {
        return dateBottling;
    }

    /**
     * @param dateBottling the dateBottling to set
     */
    public void setDateBottling(Date dateBottling) {
        this.dateBottling = dateBottling;
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
    
    public void addLabel(Language language, String label){
        this.hashLabel.put(language, label);
    }
    
    public String getLabel(Language language) {
        return hashLabel.get(language);
    }
}
