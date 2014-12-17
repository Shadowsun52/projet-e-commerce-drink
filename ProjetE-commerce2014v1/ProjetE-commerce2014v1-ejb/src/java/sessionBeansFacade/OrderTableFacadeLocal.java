/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import entityBeans.OrderTable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Local;
import model.Customer;
import model.Order;

/**
 *
 * @author Alexandre
 */
@Local
public interface OrderTableFacadeLocal {

    void create(OrderTable orderTable);

    void edit(OrderTable orderTable);

    void remove(OrderTable orderTable);

    OrderTable find(Object id);

    List<OrderTable> findAll();

    List<OrderTable> findRange(int[] range);

    int count();
    
    public boolean OrderUsingAddress(Address address);
    
    public Order converterToModel(OrderTable entity);
    
    public OrderTable converterToEntity(Order order);
    
    public ArrayList<Order> findAllOrders();
    
    public Order findOrder(Object numOrder);
    
    public ArrayList<Order> findByCustomer(Customer customer) throws Exception;
    
    public void create(Order order, ResourceBundle bundle) throws Exception;
}
