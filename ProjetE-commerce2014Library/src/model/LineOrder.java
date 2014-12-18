/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Alexandre
 */
public class LineOrder {
    private Drink drink;
    private short quantity;
    private Double price;
    
    public LineOrder() {
    }

    public LineOrder(Drink drink, short quantity, Double price) {
        this.drink = drink;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return the drink
     */
    public Drink getDrink() {
        return drink;
    }

    /**
     * @param drink the drink to set
     */
    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    /**
     * @return the quantity
     */
    public short getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    
}
