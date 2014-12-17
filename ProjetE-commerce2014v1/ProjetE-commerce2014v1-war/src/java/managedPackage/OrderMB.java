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
import javax.ejb.EJB;
import model.Customer;
import model.Order;
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
    
    public void loadOrderForCustomer(Integer customerId) throws Exception{
        orders = orderTableFacade.findByCustomer(customerId);
    }
    
    public String showOrder(Order order){
        orderSelected = order;
        return "viewOrder";  
    }
    
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
