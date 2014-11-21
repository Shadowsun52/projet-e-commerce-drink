/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import entityBeans.Address;
import entityBeans.Customer;
import entityBeans.Language;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Encryption;
import sessionBeansFacade.CustomerFacadeLocal;
import sessionBeansFacade.LanguageFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "customerMB")
@SessionScoped
public class CustomerMB implements Serializable {
    
    @EJB
    private LanguageFacadeLocal languageFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    
    private Customer customer; 
    private Boolean connected,
                    errorConnection;
    private String emailConnection,
                   passwordConnection,
                   previousPage;

    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {
        customer = new Customer();
        customer.setIdaddress(new Address());
        connected = false;
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
            customer.setInscriptiondate(new Date());
            customer.setPassword(Encryption.encryption(customer.getPassword()));
            customer.setChosenlanguage(findLanguageCurrent());
            customerFacade.create(customer);
            return "endsignup";
        }
        catch(Exception e)
        {
            System.out.println(e);
            return "error";
        }
    }
    
    public String connection(){
        customer = customerFacade.findByEmail(getEmailConnection());
        if(customer != null && PasswordCorrect()) {
            setLanguageCurrent();
            setConnected(true);
            setErrorConnection(false);
            return previousPage;
        }
        else
        {
            previousPage = FacesContext.getCurrentInstance().getViewRoot().getViewId();
            setErrorConnection(true);
            return "signin";
        }
    }
    
    public void deconnection(){
        setConnected(false);
        customer = new Customer();
    }
    
    private boolean PasswordCorrect()
    {
        String passwordProtected = Encryption.encryption(getPasswordConnection());
        return passwordProtected.equals(customer.getPassword());
    }
    
    private Language findLanguageCurrent() {
        String shortLabel = FacesContext.getCurrentInstance()
                .getViewRoot().getLocale().getLanguage();
//        return languageFacade.findByShortLabel(shortLabel);
        return null;
    }

    private void setLanguageCurrent() {
        FacesContext context = FacesContext.getCurrentInstance();
        InternationalizationManaged iM = (InternationalizationManaged) 
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{internationalizationManaged}", 
                                InternationalizationManaged.class)
                        .getValue(context.getELContext());
      iM.setLocale(customer.getChosenlanguage().getShortlabel());
    }
    
    public void setChosenLanguage(String shortLabelLanguage)
    {
//        Language LanguageChosen = languageFacade.findByShortLabel(shortLabelLanguage);
//        customer.setChosenlanguage(LanguageChosen);
//        customerFacade.edit(customer);
    }
    /**
     * @return the connected
     */
    public Boolean getConnected() {
        return connected;
    }

    /**
     * @param connected the connected to set
     */
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    /**
     * @return the emailConnection
     */
    public String getEmailConnection() {
        return emailConnection;
    }

    /**
     * @param emailConnection the emailConnection to set
     */
    public void setEmailConnection(String emailConnection) {
        this.emailConnection = emailConnection;
    }

    /**
     * @return the passwordConnection
     */
    public String getPasswordConnection() {
        return passwordConnection;
    }

    /**
     * @param passwordConnection the passwordConnection to set
     */
    public void setPasswordConnection(String passwordConnection) {
        this.passwordConnection = passwordConnection;
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

    /**
     * @return the errorConnection
     */
    public Boolean getErrorConnection() {
        return errorConnection;
    }

    /**
     * @param errorConnection the errorConnection to set
     */
    public void setErrorConnection(Boolean errorConnection) {
        this.errorConnection = errorConnection;
    }
    
    public String debug(){
        customer.getBirthdate();
        return "signup";
    }
}
