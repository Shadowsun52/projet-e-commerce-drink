/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.LineOrder;
import model.Order;
import business.MathBusiness;
import model.Promotion;
import sessionBeansFacade.OrderTableFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "orderMB")
@SessionScoped
public class OrderMB implements Serializable {
    @EJB
    private OrderTableFacadeLocal orderTableFacade;
    
    private final MathBusiness math = new MathBusiness();
    private ArrayList<Order> orders;
    private Order orderSelected;
    
    /**
     * Creates a new instance of OrderMB
     */
    public OrderMB() {
        orders = new ArrayList<>();
        orderSelected = new Order();
    }

    public String navigateToYourAccount(Integer customerId){
        try{
           loadOrderForCustomer(customerId);
            return "yourAccount"; 
        }
        catch(Exception e){
            System.out.println(e);
            return "";
        }
        
    }
    
    @SuppressWarnings("empty-statement")
    public double DiscountPromotion(Promotion promotion){
        if(promotion.getDrink() == null)
            return DiscountGeneral(promotion);
        int i=0;
        for(; !orderSelected.getLines().get(i).getDrink().equals(promotion.getDrink()); i++);
        return math.discountPromotion(promotion, 
                (int)orderSelected.getLines().get(i).getQuantity());
    }
    
    public double DiscountGeneral(Promotion promotion){
        return math.discountPromotionAll(promotion, orderSelected.getLines());
    }
    
//<editor-fold defaultstate="collapsed" desc="view">
    public void loadOrderForCustomer(Integer customerId) throws Exception{
        orders = orderTableFacade.findByCustomer(customerId);
    }
    
    public String showOrder(Order order){
        orderSelected = order;
        return "viewOrder";  
    }
    
    public boolean orderIsEmpty(){
        return orderSelected == null || orderSelected.getNumOrder() == null;
    }
    
    public void redirectionYourAccount()throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("yourAccount.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
    
    public double sumline(LineOrder line){
        return math.sumline(line.getPrice(), line.getQuantity());
    }
    
    public double sumOrder(){
        return math.sumCaddy(orderSelected);
    }
    
    public double tva(){
        return math.tva(orderSelected);
    }
    
    public double sumWithTva(){
        return math.sumWithTva(orderSelected);
    }
    
    public double sumTotal(){
        return math.sumTotalOrder(orderSelected);
    }
    
    public Integer allQuantity(){
        return math.allDrinkQuantity(orderSelected.getLines());
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getter & setter">
    /**
     * @return the orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    /**
     * @return the orderSelected
     */
    public Order getOrderSelected() {
        return orderSelected;
    }

    /**
     * @param orderSelected the orderSelected to set
     */
    public void setOrderSelected(Order orderSelected) {
        this.orderSelected = orderSelected;
    }
//</editor-fold>
}
