/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import model.Address;
import model.Customer;
import model.Encryption;
import model.InfoConnexion;
import model.Language;
import sessionBeansFacade.CustomerFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "customerMB")
@SessionScoped
public class CustomerMB implements Serializable {
    private static final String BUNDLE_LOCALE = "languagePackage.lang";
    private static final String LINK_EMAIL_SEND = "passwordForgot.xhtml?type=1";
    private static final String LINK_ERROR_EMAIL = "passwordForgot.xhtml?type=0";
    private static final String LINK_PASSWORD_EDITED = 
            "newpassword.xhtml?type=1";
    private static final String LINK_ERROR_EDITING = "newpassword.xhtml?type=0";
    
    @EJB
    private CustomerFacadeLocal customerFacade;
    
    private Customer customer; 
    private InfoConnexion infoConnexion;
    private String previousPage;
    private String newPassword;
    private String email;
    private String key;
    
    /**
     * Creates a new instance of CustomerMB
     */
    public CustomerMB() {
        customer = new Customer();
        customer.setAddress(new Address());
        infoConnexion = new InfoConnexion();
    }
 
// <editor-fold defaultstate="collapsed" desc="sign up">
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
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="sign in">  
    public String connection(){
        customer = customerFacade.findByEmail(infoConnexion.getEmail());
        if(infoConnectionCorrect()) {
            setLanguageCurrent();
            infoConnexion.setIsconnected(true);
            infoConnexion.setErrorConnection(false);
            infoConnexion.setEmail("");
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
        
    private boolean infoConnectionCorrect() {
        String passwordProtected = Encryption.encryption(
                infoConnexion.getPassword());
        return customer != null && passwordProtected.equals(
                customer.getPassword());
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
    
    public void setChosenLanguage(Language language) {
        customer.setChosenLanguage(language);
        customerFacade.edit(customer);
    }
    
    public void redirectionConnexion()throws IOException{
        try{
            previousPage = FacesContext.getCurrentInstance().getViewRoot()
                    .getViewId();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("signin.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
// </editor-fold>  

// <editor-fold defaultstate="collapsed" desc="password Forgot">
    public void redirectError() throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("error.xhtml");
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
    
    public void sendEmailPasswordForgot(){
        try{
            customerFacade.sendEmailForNewPassword(getBundle() ,
                    infoConnexion.getEmail());
            navigationWithParam(LINK_EMAIL_SEND);
        }
        catch(Exception e){
            navigationWithParam(LINK_ERROR_EMAIL);
        }
    }
    
    public boolean testParamNewPassword(){
        if(isNotEmail())
            return false;
        
        return goodKey(customerFacade.findByEmail(email));
    }
        
    public boolean test(){
        return true;
    }
    public void changePassword(){
        try {
            Customer cust = customerFacade.findByEmail(email);
            cust.setPassword(Encryption.encryption(newPassword));
            customerFacade.edit(cust);
            email = key = "";
            navigationWithParam(LINK_PASSWORD_EDITED);
        }
        catch(Exception e){
            navigationWithParam(LINK_ERROR_EDITING);
        }
    }
    
    private void navigationWithParam(String url){
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(url);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    private boolean isNotEmail() {
        if(email == null)
            return true; 
        return !Pattern.matches(
                "^[a-zA-Z0-9._-]{2,}@[a-z0-9._-]{2,}\\.[a-z]{2,4}$",email);
    }
    
    private boolean goodKey(Customer customer){
        if(customer == null) 
            return false;
        
        if(key == null)
            return false;
        return key.equals(Encryption.encryption(
                customer.getEmail() + customer.getPassword()));
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="getter & setter">
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

    /**
     * 
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
        /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
// </editor-fold>

    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
}
