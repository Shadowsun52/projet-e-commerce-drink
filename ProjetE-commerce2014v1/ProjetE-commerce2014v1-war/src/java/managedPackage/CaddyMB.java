/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Customer;
import model.DeliveryMode;
import model.Drink;
import model.Language;
import model.LineOrder;
import model.Order;
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

    private HashMap<Drink,Integer> caddy;
    private DeliveryMode delModChosen;
    /**
     * Creates a new instance of CaddyMB
     */
    public CaddyMB() {
        caddy = new HashMap();
//////////valeur test///////////////////////////////////////////////////////////
        Drink d = new Drink(10, 2.1, .33, (short)6, null);
        d.addLabel(new Language(10, "Francais", "", "fr"), "Orval");
        d.addLabel(new Language(11, "English","", "en"), "Orval");
        caddy.put(d, 6);
        
        Drink d2 = new Drink(11, 1.7, .33, (short)5, null);
        d2.addLabel(new Language(10, "Francais", "", "fr"), "Leffe");
        d2.addLabel(new Language(11, "English","", "en"), "Leffe");
        caddy.put(d2, 24);           
    }

//<editor-fold defaultstate="collapsed" desc="Management Caddy">
    public void addDrink(Drink drink, int quantity){
        if(caddy.containsKey(drink))
            caddy.put(drink, caddy.get(drink) + quantity);
        else
            caddy.put(drink, quantity);
    }
    
    public void deleteDrink(Drink drink){
        caddy.remove(drink);
    }
    
    public boolean caddyIsEmpty(){
        return caddy.isEmpty();
    }
    
    public void clearCaddy(){
        caddy.clear();
    }
    
    public double sumline(Drink drink){
        return drink.getCurrentPrice()*caddy.get(drink);
    }
    
    public double sumCaddy(){
        double sum = 0.;
        for (Map.Entry<Drink, Integer> drink : caddy.entrySet()) {
            sum += sumline(drink.getKey());
        }
        return sum;
    }
    
    public double tvaCaddy(Customer customer){
        return sumCaddy()*customer.getAddress().getCountry().getTva()/100;
    }
    
    public double sumCaddyWithTva(Customer customer){
        return sumCaddy() + tvaCaddy(customer);
    }
    
    public double sumOrder(Customer customer){
        return sumCaddyWithTva(customer) + delModChosen.getCurrentpostalcharges();
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
    public HashMap<Drink,Integer> getCaddy() {
        return caddy;
    }

    /**
     * @param caddy the caddy to set
     */
    public void setCaddy(HashMap<Drink,Integer> caddy) {
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
//</editor-fold>   

    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
}
