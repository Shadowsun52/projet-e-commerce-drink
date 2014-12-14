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
    private static final String PATCH_PAGE_NEW_PW = "http://localhost:8080/" 
            +"ProjetE-commerce2014v1-war/faces/newpassword.xhtml";
    private static final String SIGN_UP_SUBJECT = "signUpEmailSubject";
    private static final String SIGN_UP_BODY1 = "signUpEmailBody1";
    private static final String SIGN_UP_BODY2 = "signUpEmailBody2";
    private static final String FORGOT_PW_SUBJECT = "forgotPwEmailSubject";
    private static final String FORGOT_PW_BODY = "forgotPwEmailBody";
    private static final String FORGOT_PW_LINK = "forgotPwEmailLink";
    
    @EJB
    private AddressFacadeLocal addressFacade;
    @EJB
    private LanguageFacadeLocal languageFacade;
    @EJB
    private OrderTableFacadeLocal orderTableFacade;
    
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

// <editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public model.Customer findByEmail(String email) {
        try {
            Query query = em.createNamedQuery("Customer.findByEmail");
            query.setParameter("email", email);
            return converterToModel((Customer)query.getSingleResult());
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public void create(model.Customer customer, ResourceBundle bundle)
            throws Exception{
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
    
    private String createBodyForEmailSignUp(ResourceBundle bundle, String email){
        return "<h1>" + bundle.getString(SIGN_UP_SUBJECT) + "</h1><p>" 
                + bundle.getString(SIGN_UP_BODY1) + " " 
                + email + "</p><p>" + bundle.getString(SIGN_UP_BODY2) + "</p>";
    }
    
    @Override
    public void ModifyAddress(model.Customer customer, model.Address newAddress) {
        if(!addressFacade.AddressExist(newAddress, customer.getId()))
        {
            Address oldAddress = addressFacade.converterToEntity(customer.getAddress());
            customer.setAddress(newAddress);
            edit(customer);
            deleteOrphanAddress(oldAddress);
        }
    }
    
    private void deleteOrphanAddress(Address address){
        if(!orderTableFacade.OrderUsingAddress(address))
            addressFacade.remove(address);
    }
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Converter">
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
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="password Forgot">
    @Override
    public void sendEmailForNewPassword(ResourceBundle bundle, String email) 
            throws Exception{
        model.Customer customer = findByEmail(email);
        String body = "<h1>" + bundle.getString(FORGOT_PW_SUBJECT) + "</h1>" 
                + bundle.getString(FORGOT_PW_BODY) +"<p><a href=\"" 
                + createLinkNewPassword(customer) + "\">" 
                + bundle.getString(FORGOT_PW_LINK) + "</a></p>";
        EmailSender.sendEmail(email, bundle.getString(FORGOT_PW_SUBJECT), body);
    }
    
    private String createLinkNewPassword(model.Customer customer){
        return PATCH_PAGE_NEW_PW + "?email=" + customer.getEmail() +"&key="
                + Encryption.encryption(
                        customer.getEmail() + customer.getPassword());
    }
// </editor-fold>
}
