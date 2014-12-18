/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Customer;
import model.DeliveryMode;
import model.Drink;
import model.LineOrder;
import model.Order;
import business.MathBusiness;
import model.Promotion;
import sessionBeansFacade.OrderTableFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "caddyMB")
@SessionScoped
public class CaddyMB implements Serializable {
    private static final String BUNDLE_LOCALE = "languagePackage.lang";
    private static final String PAGE_ORDER_VALID = "orderEnd";
    private static final String PAGE_ORDER_ERROR = "orderError";
    
    @EJB
    private OrderTableFacadeLocal orderTableFacade;

    private final MathBusiness math = new MathBusiness();
    private HashMap<Drink,BigDecimal> caddy;
    private DeliveryMode delModChosen;
    private HashMap<Drink,Promotion> promotions;
    /**
     * Creates a new instance of CaddyMB
     */
    public CaddyMB() {
        caddy = new HashMap(); 
        promotions = new HashMap<>();
    }

//<editor-fold defaultstate="collapsed" desc="Management Caddy">
    public void addDrink(Drink drink, int quantity, Promotion promoDrink){
        if(caddy.containsKey(drink))
            caddy.put(drink, new BigDecimal(caddy.get(drink).intValue() + quantity));
        else{
            caddy.put(drink, new BigDecimal(quantity));
        }
        AddPromoIfExist(drink, caddy.get(drink).intValue());
    }
    
    public void deleteDrink(Drink drink){
        caddy.remove(drink);
        promotions.remove(drink);
    }
    
    public boolean caddyIsEmpty(){
        return caddy.isEmpty();
    }
    
    public void clearCaddy(){
        getPromotions().clear();
        caddy.clear();
    }
    
    public double sumline(Drink drink){
        return math.sumline(drink, caddy.get(drink).shortValue());
    }
    
    public double sumCaddy(){
        return math.sumCaddy(promotions,caddy);
    }
    
    public double tvaCaddy(Customer customer){
        return math.tva(caddy, customer);
    }
    
    public double sumCaddyWithTva(Customer customer){
        return math.sumWithTva(promotions, caddy, customer);
    }
    
    public double sumOrder(Customer customer){
        return math.sumTotalOrder(promotions, caddy, customer, delModChosen);
    }
    
//</editor-fold>   
    
//<editor-fold defaultstate="collapsed" desc="Promotion">
    
    private void AddPromoIfExist(Drink drink, Integer quantity){
        readAllPromotions().stream().forEach((promotion)->{
            if(promotion.getDrink() != null && promotion.getDrink().equals(drink))
                if(PrerequisAddPromotion(promotion, quantity))
                    promotions.put(drink,promotion);                    
        });
    }
    
    private ArrayList<Promotion> readAllPromotions (){
        FacesContext context = FacesContext.getCurrentInstance();
        PromotionMB promotionMB = (PromotionMB) 
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{promotionMB}", 
                                PromotionMB.class)
                        .getValue(context.getELContext());
        return promotionMB.getPromotions();
    }
    
    private boolean PrerequisAddPromotion(Promotion promoDrink, int quantity) {
        if(promoDrink.getCodePromo() != null)
            return false;
        
        if(promotionActif(promoDrink))
            return promoDrink.getMinQuantity() == null || quantity >= promoDrink.getMinQuantity();
        else
            return false;
    }
    
    private boolean promotionActif(Promotion promoDrink){
        Date now = new Date();
        return now.compareTo(promoDrink.getDateStart()) >= 0 && now.compareTo(promoDrink.getDateEnd()) <= 0;
    }
    
    public void promotionMinQuantityOk(Drink drink){
        if(promotions.containsKey(drink))
            if(!(promotions.get(drink).getMinQuantity() == null 
                || caddy.get(drink).intValue() >= promotions.get(drink).getMinQuantity()))
            promotions.remove(drink);
    }
    
    public void UpdatePromotion(Drink drink){
        AddPromoIfExist(drink, caddy.get(drink).intValue());
        promotionMinQuantityOk(drink);
    }
    
    public double DiscountPromotion(Promotion promotion){
        return math.discountPromotion(promotion, 
                caddy.get(promotion.getDrink()).intValue());
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Order">
    public String saveOrder() {
        try{
            Order order = createModelOrder();
            orderTableFacade.create(order, getBundle());
            delModChosen = null;
            clearCaddy();
            return PAGE_ORDER_VALID;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return PAGE_ORDER_ERROR;
        }
    }
    
    private Order createModelOrder(){
        FacesContext context = FacesContext.getCurrentInstance();
        CustomerMB customerMB = (CustomerMB) 
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{customerMB}", 
                                CustomerMB.class)
                        .getValue(context.getELContext());
        Customer customer = customerMB.getCustomer();
        Order order = new Order(null, new Date(), new Date(), 
                delModChosen.getCurrentpostalcharges(), delModChosen, 
                customer.getAddress(), customer);
        order.setLines(converterToLineOrder());
        return order;
    }
    
    private ArrayList<LineOrder> converterToLineOrder(){
        ArrayList<LineOrder> lines = new ArrayList();
        caddy.forEach((drink,quantity) -> {
            lines.add(new LineOrder(drink, quantity.shortValue(), 
                    drink.getCurrentPrice()));
        });
        return lines;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getter and setter">
    /**
     * @return the caddy
     */
    public HashMap<Drink,BigDecimal> getCaddy() {
        return caddy;
    }

    /**
     * @param caddy the caddy to set
     */
    public void setCaddy(HashMap<Drink,BigDecimal> caddy) {
        this.caddy = caddy;
    }
    
    public List<Entry<Drink,Integer>> getListCaddy(){
        return new ArrayList(caddy.entrySet());
    }
    
    /**
     * @return the delModChosen
     */
    public DeliveryMode getDelModChosen() {
        return delModChosen;
    }

    /**
     * @param delModChosen the delModChosen to set
     */
    public void setDelModChosen(DeliveryMode delModChosen) {
        this.delModChosen = delModChosen;
    }
    
    /**
     * @return the promotions
     */
    public HashMap<Drink,Promotion> getPromotions() {
        return promotions;
    }

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(HashMap<Drink,Promotion> promotions) {
        this.promotions = promotions;
    }
    
    public List<Entry<Drink,Promotion>> getListPromotion(){
        return new ArrayList(promotions.entrySet());
    }
//</editor-fold>   

    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
}
