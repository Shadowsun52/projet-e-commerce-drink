/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Alexandre
 */
public class Order {
    private Integer numOrder;
    private Date validationDate;
    private Date paymentdate;
    private Double postalcharges;
    private DeliveryMode deliveryMode;
    private Address delAddress;
    private Customer customer;
    private ArrayList<LineOrder> lines;

    public Order() {
    }

    public Order(Integer numOrder, Date validationDate, Date paymentdate, 
            Double postalcharges, DeliveryMode deliveryMode, Address delAddress, 
            Customer customer) {
        this.numOrder = numOrder;
        this.validationDate = validationDate;
        this.paymentdate = paymentdate;
        this.postalcharges = postalcharges;
        this.deliveryMode = deliveryMode;
        this.delAddress = delAddress;
        this.customer = customer;
        this.lines = new ArrayList<>();
    }
    
    /**
     * @return the numOrder
     */
    public Integer getNumOrder() {
        return numOrder;
    }

    /**
     * @param numOrder the numOrder to set
     */
    public void setNumOrder(Integer numOrder) {
        this.numOrder = numOrder;
    }

    /**
     * @return the validationDate
     */
    public Date getValidationDate() {
        return validationDate;
    }

    /**
     * @param validationDate the validationDate to set
     */
    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

    /**
     * @return the paymentdate
     */
    public Date getPaymentdate() {
        return paymentdate;
    }

    /**
     * @param paymentdate the paymentdate to set
     */
    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    /**
     * @return the postalcharges
     */
    public Double getPostalcharges() {
        return postalcharges;
    }

    /**
     * @param postalcharges the postalcharges to set
     */
    public void setPostalcharges(Double postalcharges) {
        this.postalcharges = postalcharges;
    }

    /**
     * @return the deliveryMode
     */
    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    /**
     * @param deliveryMode the deliveryMode to set
     */
    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    /**
     * @return the delAddress
     */
    public Address getDelAddress() {
        return delAddress;
    }

    /**
     * @param delAddress the delAddress to set
     */
    public void setDelAddress(Address delAddress) {
        this.delAddress = delAddress;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the lines
     */
    public ArrayList<LineOrder> getLines() {
        return lines;
    }

    /**
     * @param lines the lines to set
     */
    public void setLines(ArrayList<LineOrder> lines) {
        this.lines = lines;
    }
    
    
}
