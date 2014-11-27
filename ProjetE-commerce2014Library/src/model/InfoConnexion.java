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
public final class InfoConnexion implements Serializable{
    private String email;
    private String password;
    private boolean isconnected;
    private boolean errorConnection;

    public InfoConnexion() {
        setIsconnected(false);
        setErrorConnection(false);
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
     * @return the isconnected
     */
    public boolean isIsconnected() {
        return isconnected;
    }

    /**
     * @param isconnected the isconnected to set
     */
    public void setIsconnected(boolean isconnected) {
        this.isconnected = isconnected;
    }

    /**
     * @return the errorConnection
     */
    public boolean isErrorConnection() {
        return errorConnection;
    }

    /**
     * @param errorConnection the errorConnection to set
     */
    public void setErrorConnection(boolean errorConnection) {
        this.errorConnection = errorConnection;
    }
    
    
}
