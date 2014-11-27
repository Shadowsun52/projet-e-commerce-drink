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
public class Address implements Serializable{
    private Integer id;
    private String nameStreet;
    private String numStreet;
    private String postCode;
    private String city;
    private Country country;

    public Address() {
    }

    public Address(Integer id, String nameStreet, String numStreet, 
            String postCode, String city, Country country) {
        this.id = id;
        this.nameStreet = nameStreet;
        this.numStreet = numStreet;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
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
     * @return the nameStreet
     */
    public String getNameStreet() {
        return nameStreet;
    }

    /**
     * @param nameStreet the nameStreet to set
     */
    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    /**
     * @return the numStreet
     */
    public String getNumStreet() {
        return numStreet;
    }

    /**
     * @param numStreet the numStreet to set
     */
    public void setNumStreet(String numStreet) {
        this.numStreet = numStreet;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }
    
}
