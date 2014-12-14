/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import entityBeans.OrderTable;
import java.util.List;
import javax.ejb.Local;

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
    
}
