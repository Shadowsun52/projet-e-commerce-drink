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
import sessionBeansFacade.PromotionFacadeLocal;

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
    @EJB
    private PromotionFacadeLocal promotionFacade;
    
    private final MathBusiness math = new MathBusiness();
    private HashMap<Drink,BigDecimal> caddy;
    private DeliveryMode delModChosen;
    private ArrayList<Promotion> promotions;
    private String codePromotion;
    /**
     * Creates a new instance of CaddyMB
     */
    public CaddyMB() {
        caddy = new HashMap(); 
        promotions = new ArrayList<>();
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
        Integer pos = findPromotionInList(drink);
        if(pos != null)
            promotions.remove((int)pos);
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
                if(PrerequisAddPromotion(promotion, quantity)){
                    Integer pos = findPromotionInList(drink);
                    if(pos == null)
                        promotions.add(promotion);
                }                             
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
        Integer pos = findPromotionInList(drink);
        if(pos != null)
            if(!(promotions.get(pos).getMinQuantity() == null 
                || caddy.get(drink).intValue() >= promotions.get(pos).getMinQuantity()))
            promotions.remove((int)pos);
    }
    
    public void UpdatePromotion(Drink drink){
        AddPromoIfExist(drink, caddy.get(drink).intValue());
        promotionMinQuantityOk(drink);
    }
    
    public double DiscountPromotion(Promotion promotion){
        if(promotion.getDrink() == null)
            return DiscountGeneral(promotion);
        return math.discountPromotion(promotion, 
                caddy.get(promotion.getDrink()).intValue());
    }
    
    public double DiscountGeneral(Promotion promotion){
        return math.discountPromotionAll(promotion, caddy);
    }
    
    public Integer allQuantity(){
        return math.allDrinkQuantity(caddy);
    }
    
    @SuppressWarnings("empty-statement")
    public Integer findPromotionInList(Drink drink){
        int pos = 0;
        for(;pos <promotions.size();pos++){
            if(promotions.get(pos).getDrink() != null 
                    && promotions.get(pos).getDrink().equals(drink))
                break;
        }
        if(pos == promotions.size())
            return null;
        return pos;
    }
    
    public void addPromoCode(){
        Promotion promotion = promotionFacade.findByCodePromo(codePromotion);
        promotions.add(promotion);
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
        order.setPromotions(promotions);
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
    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }
    
    /**
     * @return the codePromotion
     */
    public String getCodePromotion() {
        return codePromotion;
    }

    /**
     * @param codePromotion the codePromotion to set
     */
    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
    }
//</editor-fold>   

    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
}
