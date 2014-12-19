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
    
    public double sumCaddy(HashMap<Drink, BigDecimal> caddy){
        double sum = 0.;
        for (Map.Entry<Drink, BigDecimal> drink : caddy.entrySet()) {
            sum += sumline(drink.getKey(), drink.getValue().shortValue());
        }
        return sum;
    }
    
    public double sumCaddy(ArrayList<Promotion> promotions,
            HashMap<Drink, BigDecimal> caddy){
        return sumCaddy(caddy) + discountPromotions(promotions, caddy);
    }
    
    public double sumCaddy(ArrayList<LineOrder> lines){
        double sum = 0.;
        for (LineOrder line : lines) {
            sum += sumline(line.getPrice(), line.getQuantity());
        }
        return sum;
    }
    
    public double sumCaddy(Order order){
        return sumCaddy(order.getLines()) 
                + discountPromotions(order.getPromotions(), order.getLines());
    }
    
    public double tva(HashMap<Drink, BigDecimal> caddy, Customer customer){
        return sumCaddy(caddy)*customer.getAddress().getCountry().getTva()/100;
    }
    
    public double tva(Order order){
        return sumCaddy(order.getLines())
                *order.getCustomer().getAddress().getCountry().getTva()/100;
    }
    
    public double sumWithTva(ArrayList<Promotion> promotions,
            HashMap<Drink, BigDecimal> caddy, Customer customer){
        return sumCaddy(promotions, caddy) + tva(caddy, customer);
    }
    
    public double sumWithTva(Order order){
        return sumCaddy(order) + tva(order);
    }
    
    public double sumTotalOrder(ArrayList<Promotion> promotions,
            HashMap<Drink, BigDecimal> caddy, Customer customer, 
            DeliveryMode delmode){
        return sumWithTva(promotions,caddy, customer) 
                + delmode.getCurrentpostalcharges();
    }
    
    public double sumTotalOrder(Order order){
        return sumWithTva(order) + order.getPostalcharges();
    }
    
    public double discountPromotion(Promotion promotion, Integer quantity){
        if(promotion.getTypeDiscount().equals(Promotion.TYPE_PERCENTAGE))
            return -promotion.getDrink().getCurrentPrice()*
                    (promotion.getPercentageDiscount()/100.)*quantity;
        else
            return -promotion.getAmountDiscount().doubleValue();
    }
    
    public double discountPromotions(ArrayList<Promotion> promotions, 
            HashMap<Drink, BigDecimal> caddy){
        double sum = 0.;
        for (Promotion promotion : promotions) {
            sum += discountPromotion(promotion, 
                    caddy.get(promotion.getDrink()).intValue());
        }
        return sum;
    }
    
    public double discountPromotions(ArrayList<Promotion> promotions, 
            ArrayList<LineOrder> lines){
        double sum = 0.;
        for (Promotion promotion : promotions) {
            sum += discountPromotion(promotion, 
                    findQuantityForDrink(lines, promotion.getDrink()));
        }
        return sum;
    }
    
    @SuppressWarnings("empty-statement")
    private Integer findQuantityForDrink(ArrayList<LineOrder> lines, Drink drink){
        int i = 0;
        for(; !lines.get(i).getDrink().equals(drink); i++);
        return (int)lines.get(i).getQuantity();
    }
    
    public Integer allDrinkQuantity(HashMap<Drink, BigDecimal> caddy){
        Integer sumQuantity = 0;
        for (Map.Entry<Drink, BigDecimal> entrySet : caddy.entrySet()) {
            sumQuantity += entrySet.getValue().intValue();
        }
        return sumQuantity;
    }
    
    public Integer allDrinkQuantity(ArrayList<LineOrder> lines){
        Integer sumQuantity = 0;
        for (LineOrder line : lines) {
            sumQuantity += line.getQuantity();
        }
        return sumQuantity;
    }
}