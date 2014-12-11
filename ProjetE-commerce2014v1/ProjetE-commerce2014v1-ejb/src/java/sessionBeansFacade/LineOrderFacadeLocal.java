/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LineOrder;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LineOrderFacadeLocal {

    void create(LineOrder lineOrder);

    void edit(LineOrder lineOrder);

    void remove(LineOrder lineOrder);

    LineOrder find(Object id);

    List<LineOrder> findAll();

    List<LineOrder> findRange(int[] range);

    int count();
    
}
