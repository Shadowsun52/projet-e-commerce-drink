/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Alexandre
 */
public class Customer implements Serializable{
    private Integer id;
    private String name;
    private String lastname;
    private Date birthdate;
    private Address address;
    private String numphone;
    private String email;
    private String password;
    private Date inscriptionDate;
    private Language chosenLanguage;

    public Customer() {
        address = new Address();
    }

    public Customer(Integer id, String name, String lastname, 
            Date birthdate, Address adress, String numphone, 
            String email, String password, Date inscriptionDate, 
            Language chosenLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.address = adress;
        this.numphone = numphone;
        this.email = email;
        this.password = password;
        this.inscriptionDate = inscriptionDate;
        this.chosenLanguage = chosenLanguage;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
         this.birthdate = birthdate;
    }

    /**
     * @return the adress
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param adress the adress to set
     */
    public void setAddress(Address adress) {
        this.address = adress;
    }

    /**
     * @return the numphone
     */
    public String getNumphone() {
        return numphone;
    }

    /**
     * @param numphone the numphone to set
     */
    public void setNumphone(String numphone) {
        this.numphone = numphone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the inscriptionDate
     */
    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    /**
     * @param inscriptionDate the inscriptionDate to set
     */
    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    /**
     * @return the chosenLanguage
     */
    public Language getChosenLanguage() {
        return chosenLanguage;
    }

    /**
     * @param chosenLanguage the chosenLanguage to set
     */
    public void setChosenLanguage(Language chosenLanguage) {
        this.chosenLanguage = chosenLanguage;
    }
}
