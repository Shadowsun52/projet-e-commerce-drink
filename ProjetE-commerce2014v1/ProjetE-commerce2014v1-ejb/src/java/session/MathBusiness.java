/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Customer;
import model.DeliveryMode;
import model.Drink;
import model.LineOrder;
import model.Order;

/**
 *
 * @author Alexandre
 */
public class MathBusiness {

    public MathBusiness() {
    }
    
    public double sumline(Drink drink, short quantity){
        return sumline(drink.getCurrentPrice(), quantity);
    }
    
    public double sumline(double unitPrice, short quantity){
        return unitPrice*quantity;
    }
    
    public double sumCaddy(HashMap<Drink, BigDecimal> caddy){
        double sum = 0.;
        for (Map.Entry<Drink, BigDecimal> drink : caddy.entrySet()) {
            sum += sumline(drink.getKey(), drink.getValue().shortValue());
        }
        return sum;
    }
    
    public double sumCaddy(ArrayList<LineOrder> lines){
        double sum = 0.;
        for (LineOrder line : lines) {
            sum += sumline(line.getPrice(), line.getQuantity());
        }
        return sum;
    }
    
    public double tva(HashMap<Drink, BigDecimal> caddy, Customer customer){
        return sumCaddy(caddy)*customer.getAddress().getCountry().getTva()/100;
    }
    
    public double tva(ArrayList<LineOrder> lines, Customer customer){
        return sumCaddy(lines)*customer.getAddress().getCountry().getTva()/100;
    }
    
    public double sumWithTva(HashMap<Drink, BigDecimal> caddy,
            Customer customer){
        return sumCaddy(caddy) + tva(caddy, customer);
    }
    
    public double sumWithTva(ArrayList<LineOrder> lines,
            Customer customer){
        return sumCaddy(lines) + tva(lines, customer);
    }
    
    public double sumTotalOrder(HashMap<Drink, BigDecimal> caddy, Customer customer, 
            DeliveryMode delmode){
        return sumWithTva(caddy, customer) + delmode.getCurrentpostalcharges();
    }
    
    public double sumTotalOrder(Order order){
        return sumWithTva(order.getLines(), order.getCustomer()) 
                + order.getPostalcharges();
    }
}
