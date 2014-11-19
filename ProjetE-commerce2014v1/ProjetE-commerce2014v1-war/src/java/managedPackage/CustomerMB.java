/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import entityBeans.Customer;
import entityBeans.Language;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import sessionBeansFacade.CustomerFacadeLocal;
import sessionBeansFacade.LanguageFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "customerMB")
@SessionScoped
public class CustomerMB implements Serializable {
    private static final String SALT = "KIOPURTE";
    
    @EJB
    private LanguageFacadeLocal languageFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    
    private Customer customer; 
    private Boolean connected;
    private String emailConnection,
                   passwordConnection;

    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {
        customer = new Customer();
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
            customer.setPassword(encryption(customer.getPassword()));
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
    
    public void connection(){
        customer = customerFacade.findByEmail(getEmailConnection());
        if(customer != null && PasswordCorrect()) {
            setLanguageCurrent();
            setConnected(true);  
        }         
    }
    
    public void deconnection(){
        setConnected(false);
    }
    
    private boolean PasswordCorrect()
    {
        String passwordProtected = encryption(getPasswordConnection());
        return passwordProtected.equals(customer.getPassword());
    }
    
    private String encryption(String input)
    {
        try{
            String password = input + SALT;
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        }
        catch(Exception e){
            System.err.println(e);
            return null;
        }    
    }
    
    private Language findLanguageCurrent() {
        String shortLabel = FacesContext.getCurrentInstance()
                .getViewRoot().getLocale().getLanguage();
        return languageFacade.findByShortLabel(shortLabel);
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
        Language LanguageChosen = languageFacade.findByShortLabel(shortLabelLanguage);
        customer.setChosenlanguage(LanguageChosen);
        customerFacade.edit(customer);
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
}
