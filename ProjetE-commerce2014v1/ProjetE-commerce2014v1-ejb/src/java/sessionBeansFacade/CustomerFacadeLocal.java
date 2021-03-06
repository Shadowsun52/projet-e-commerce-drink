/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface CustomerFacadeLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();
    
    public model.Customer findByEmail(String email);
            
    public void create(model.Customer customer, ResourceBundle bundle) throws Exception;
    
    public void edit(model.Customer customer);
    
    public model.Customer findCustomer(Object id);
    
    public ArrayList<model.Customer> findAllCustomers();
    
    public model.Customer converterToModel(Customer entity);
    
    public Customer converterToEntity(model.Customer customer);
    
    public void sendEmailForNewPassword(ResourceBundle bundle, String email) throws Exception;
    
    public void ModifyAddress(model.Customer customer, model.Address newAddress);
    
    public void ModifyMail(model.Customer customer, String mail);
}
