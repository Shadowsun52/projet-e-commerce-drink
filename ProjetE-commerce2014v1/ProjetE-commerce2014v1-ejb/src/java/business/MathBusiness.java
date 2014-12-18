/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Customer;
import model.DeliveryMode;
import model.Drink;
import model.LineOrder;
import model.Order;
import model.Promotion;

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
    
    public double sumWithPromotion(double unitPrice, short quantity, 
            Promotion promotion){
        if(promotion != null && minQuantitySelected(promotion.getMinQuantity(), quantity))
        {
            if(promotion.getTypeDiscount().equals(Promotion.TYPE_AMOUNT))
                return sumline(unitPrice, quantity) 
                        - promotion.getAmountDiscount().doubleValue();

            return substractPromoPercentage(promotion, sumline(unitPrice, quantity));
        }
        return sumline(unitPrice, quantity);
    }
    
    private boolean minQuantitySelected(Short min, short quantity){
        return min == null || quantity >= min;
    }
    
    public double substractPromoPercentage(Promotion promotion, double value){
        return value*(1-(promotion.getPercentageDiscount()/100.));
    }
    
    public double sumCaddy(HashMap<Drink, Promotion> promotions,
            HashMap<Drink, BigDecimal> caddy){
        double sum = sumCaddy(caddy);
        sum += discountPromotions(promotions, caddy);
        return sum;
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
    
    public double sumWithTva(HashMap<Drink, Promotion> promotions,
            HashMap<Drink, BigDecimal> caddy, Customer customer){
        return sumCaddy(promotions, caddy) + tva(caddy, customer);
    }
    
    public double sumWithTva(ArrayList<LineOrder> lines,
            Customer customer){
        return sumCaddy(lines) + tva(lines, customer);
    }
    
    public double sumTotalOrder(HashMap<Drink, Promotion> promotions,
            HashMap<Drink, BigDecimal> caddy, Customer customer, 
            DeliveryMode delmode){
        return sumWithTva(promotions,caddy, customer) 
                + delmode.getCurrentpostalcharges();
    }
    
    public double sumTotalOrder(Order order){
        return sumWithTva(order.getLines(), order.getCustomer()) 
                + order.getPostalcharges();
    }
    
    public double discountPromotion(Promotion promotion, Integer quantity){
        if(promotion.getTypeDiscount().equals(Promotion.TYPE_PERCENTAGE))
            return -promotion.getDrink().getCurrentPrice()*
                    (promotion.getPercentageDiscount()/100.)*quantity;
        else
            return -promotion.getAmountDiscount().doubleValue();
    }
    
    public double discountPromotions(HashMap<Drink, Promotion> promotions, 
            HashMap<Drink, BigDecimal> caddy){
        double sum = 0.;
        for (Map.Entry<Drink, Promotion> promotion : promotions.entrySet()) {
            sum += discountPromotion(promotion.getValue(), caddy.get(promotion.getKey()).intValue());
        }
        return sum;
    }
}
