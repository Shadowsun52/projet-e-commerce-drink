/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Deliverymode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface DeliverymodeFacadeLocal {

    void create(Deliverymode deliverymode);

    void edit(Deliverymode deliverymode);

    void remove(Deliverymode deliverymode);

    Deliverymode find(Object id);

    List<Deliverymode> findAll();

    List<Deliverymode> findRange(int[] range);

    int count();
    
    public model.DeliveryMode converterToModel(Deliverymode entity);
    
    public model.DeliveryMode findDeliveryMode(Object id);
    
    public ArrayList<model.DeliveryMode> findAllDeliveryMode();
}
