/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Address;
import model.Customer;
import model.EmailSender;
import model.Encryption;
import model.InfoConnexion;
import model.Language;
import sessionBeansFacade.CustomerFacadeLocal;
import sessionBeansFacade.LanguageFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "customerMB")
@SessionScoped
public class CustomerMB implements Serializable {
    private static final String BUNDLE_LOCALE = "languagePackage.lang";
    
    @EJB
    private LanguageFacadeLocal languageFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    
    private Customer customer; 
    private InfoConnexion infoConnexion;
    private String previousPage;
    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {
        customer = new Customer();
        customer.setAddress(new Address());
        infoConnexion = new InfoConnexion();
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public String signUp(){
        try{
            customer.setChosenLanguage(findLanguageCurrent());
            customerFacade.create(customer, getBundle());
            return "endsignup";
        }
        catch(Exception e)
        {
            System.out.println(e);
            return "error";
        }
    }
    
    public String connection(){
        customer = customerFacade.findByEmail(infoConnexion.getEmail());
        if(infoConnectionCorrect()) {
            setLanguageCurrent();
            infoConnexion.setIsconnected(true);
            infoConnexion.setErrorConnection(false);
            return previousPage;
        }
        else
        {
            previousPage = FacesContext.getCurrentInstance().getViewRoot()
                    .getViewId();
            infoConnexion.setErrorConnection(true);
            return "signin";
        }
    }
    
    public void deconnection(){
        infoConnexion.setIsconnected(false);
        infoConnexion.setErrorConnection(false);
        customer = new Customer();
    }
    
    private boolean infoConnectionCorrect()
    {
        String passwordProtected = Encryption.encryption(
                infoConnexion.getPassword());
        return customer != null && passwordProtected.equals(
                customer.getPassword());
    }
    
    private Language findLanguageCurrent() {
        FacesContext context = FacesContext.getCurrentInstance();
        InternationalizationManaged im = (InternationalizationManaged) context
                .getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), 
                        "#{internationalizationManaged}", 
                        InternationalizationManaged.class)
                .getValue(context.getELContext());
        return im.getCurrentLanguage();
    }

    private void setLanguageCurrent() {
        FacesContext context = FacesContext.getCurrentInstance();
        InternationalizationManaged im = (InternationalizationManaged) 
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{internationalizationManaged}", 
                                InternationalizationManaged.class)
                        .getValue(context.getELContext());
        im.setLocale(customer.getChosenLanguage());
    }
    
    public void setChosenLanguage(Language language)
    {
        customer.setChosenLanguage(language);
        customerFacade.edit(customer);
    }

    /**
     * @return the previousPage
     */
    public String getPreviousPage() {
        return previousPage;
    }

    /**
     * @param previousPage the previousPage to set
     */
    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }
    
    public String debug(){
        customer.getBirthdate();
        return "signup";
    }

    /**
     * @return the infoConnexion
     */
    public InfoConnexion getInfoConnexion() {
        return infoConnexion;
    }

    /**
     * @param infoConnexion the infoConnexion to set
     */
    public void setInfoConnexion(InfoConnexion infoConnexion) {
        this.infoConnexion = infoConnexion;
    }
    
    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
}
