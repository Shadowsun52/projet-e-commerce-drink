/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import entityBeans.Customer;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.EmailSender;
import model.Encryption;

/**
 *
 * @author Alexandre
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {  
    private static final String SIGN_UP_SUBJECT = "signUpEmailSubject";
    private static final String SIGN_UP_BODY1 = "signUpEmailBody1";
    private static final String SIGN_UP_BODY2 = "signUpEmailBody2";
    
    @EJB
    private AddressFacadeLocal addressFacade;
    @EJB
    private LanguageFacadeLocal languageFacade;
    
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    public model.Customer findByEmail(String email) {
        try {
            Query query;
            query = em.createNamedQuery("Customer.findByEmail");
            query.setParameter("email", email);
            return converterToModel((Customer)query.getSingleResult());
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void create(model.Customer customer, ResourceBundle bundle) {
        try{
        customer.setPassword(Encryption.encryption(customer.getPassword()));
        Address address = addressFacade.converterToEntity(customer.getAddress());
        addressFacade.create(address);
        Customer entity = converterToEntity(customer);
        entity.setIdaddress(address);
        create(entity);
        EmailSender.sendEmail(customer.getEmail(), 
                bundle.getString(SIGN_UP_SUBJECT), 
                createBodyForEmailSignUp(bundle, customer.getEmail()));
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    public void edit(model.Customer customer) {
        edit(converterToEntity(customer));
    }

    @Override
    public model.Customer findCustomer(Object id) {
        return converterToModel(find(id));
    }

    @Override
    public ArrayList<model.Customer> findAllCustomers() {
        ArrayList<model.Customer> listCustomers = new ArrayList();
        findAll().stream().forEach((customer) ->{
            listCustomers.add(converterToModel(customer));
        });
        return listCustomers;
    }
    
    @Override
    public model.Customer converterToModel(Customer entity){
        model.Address address = addressFacade.converterToModel(
                entity.getIdaddress());
        
        model.Language chosenLanguage = languageFacade.converterToModel(
                entity.getChosenlanguage());
        
        return new model.Customer(entity.getIdcustomer(), entity.getName(), 
                entity.getLastname(), entity.getBirthdate(), address, 
                entity.getNumphone(), entity.getEmail(), entity.getPassword(), 
                entity.getInscriptiondate(), chosenLanguage);
    }

    @Override
    public Customer converterToEntity(model.Customer customer) {
        Customer entity = new Customer(customer.getId(), customer.getName(), 
                customer.getLastname(), customer.getBirthdate(), 
                customer.getNumphone(), customer.getEmail(), 
                customer.getPassword(), (customer.getInscriptionDate() == null)
                        ? new Date():customer.getInscriptionDate());
        
        entity.setIdaddress(
                addressFacade.converterToEntity(customer.getAddress()));
        
        entity.setChosenlanguage(
                languageFacade.find(customer.getChosenLanguage().getId()));
        
        return entity;
    }
    
    private String createBodyForEmailSignUp(ResourceBundle bundle, String email){
        return "<h1>" + bundle.getString(SIGN_UP_SUBJECT) + "</h1><p>" 
                + bundle.getString(SIGN_UP_BODY1) + " " 
                + email + "</p><p>" + bundle.getString(SIGN_UP_BODY2) + "</p>";
    }
}
