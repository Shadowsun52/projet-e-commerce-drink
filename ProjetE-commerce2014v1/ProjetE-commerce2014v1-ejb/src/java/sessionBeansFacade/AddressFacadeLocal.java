/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface AddressFacadeLocal {

    void create(Address address);

    void edit(Address address);

    void remove(Address address);

    Address find(Object id);

    List<Address> findAll();

    List<Address> findRange(int[] range);

    int count();
    
    public model.Address converterToModel(Address entity);
    
    public Address converterToEntity(model.Address address);
    
    public void create(model.Address address);
    
    public void edit(model.Address address);
    
    public model.Address findAddress(Object id);
    
    public ArrayList<model.Address> findAllAddress();
}
