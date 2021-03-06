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
    private static final String PAGE_SIGN_IN = "signin.xhtml";
    private static final String PAGE_INDEX = "index.xhtml";
    private static final String LINK_PASSWORD_MODIFIED = 
            "modifiedPassword.xhtml";
    private static final String LINK_PASSWORD_MODIFIED_FAIL = 
            "modifiedPasswordFail.xhtml";
    
    @EJB
    private CustomerFacadeLocal customerFacade;
    
    private Customer customer; 
    private InfoConnexion infoConnexion;
    private Address newAddress;
    private String previousPage;
    private String newPassword;
    private String oldPassword;
    private String newPhone;
    private String email;
    private String key;
    private String newEmail;

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
            testNumPhone();
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
    
    public void testNumPhone(){
        if(customer.getNumphone().equals(""))
            customer.setNumphone(null);
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
//</editor-fold>

// <editor-fold defaultstate="collapsed" desc="sign in">  
    public String connection(){
        customer = customerFacade.findByEmail(infoConnexion.getEmail());
        if(infoConnectionCorrect()) {
            setLanguageCurrent();
            infoConnexion.setIsconnected(true);
            infoConnexion.setErrorConnection(false);
            infoConnexion.setEmail("");
            if(currentPage().equals("/signin.xhtml"))
                return previousPage;
            return "";
        }
        else
        {
            if(!currentPage().equals("/signin.xhtml"))
                previousPage = currentPage();
            infoConnexion.setErrorConnection(true);
            return "signin";
        }
    }
    
    public String deconnection(){
        infoConnexion.setIsconnected(false);
        infoConnexion.setErrorConnection(false);
        customer = new Customer();
        return "index";
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
            previousPage = currentPage();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(PAGE_SIGN_IN);
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
    
    public void redirectionIndex()throws IOException{
        try{
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(PAGE_INDEX);
        }
        catch(IOException e){
            System.out.println("error redirect.");
        }
    }
    
    public String currentPage(){
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
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

//<editor-fold defaultstate="collapsed" desc="Change Address">
    public String navNewAddress(){
        previousPage = currentPage();
        return "modifyAddress";
    }
    
    public void initNewAddress(){
        newAddress = new Address(null, customer.getAddress().getNameStreet(), 
                customer.getAddress().getNumStreet(), 
                customer.getAddress().getPostCode(), 
                customer.getAddress().getCity(), 
                customer.getAddress().getCountry());
        newPhone = customer.getNumphone();
    }
    
    public String cancelModify(){
        return previousPage;
    }
    
    public String modifyAddress(){
        customerFacade.ModifyAddress(customer, newAddress);
        return previousPage;
    }
    
    public String modifyMail(){
        customerFacade.ModifyMail(customer, newEmail);
        newEmail=null;
        return previousPage;
    }
    
    public void modifyPassword(){
        
        String passwordProtected = Encryption.encryption(
                oldPassword);
        if(passwordProtected.equals(customer.getPassword())){
            try {
                customer.setPassword(Encryption.encryption(newPassword));
                customerFacade.edit(customer);
                navigationWithParam(LINK_PASSWORD_MODIFIED);
            }
            catch(Exception e){
                navigationWithParam(LINK_ERROR_EDITING);
            }
            oldPassword=null;
            newPassword=null;
        }
        else
            navigationWithParam(LINK_PASSWORD_MODIFIED_FAIL);
    }
//</editor-fold>
    
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
    
    /**
     * @return the newAddress
     */
    public Address getNewAddress() {
        return newAddress;
    }

    /**
     * @param newAddress the newAddress to set
     */
    public void setNewAddress(Address newAddress) {
        this.newAddress = newAddress;
    }

    /**
     * @return the newPhone
     */
    public String getNewPhone() {
        return newPhone;
    }

    /**
     * @param newPhone the newPhone to set
     */
    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }
    
    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
    
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
// </editor-fold>

    private ResourceBundle getBundle(){
        return ResourceBundle.getBundle(BUNDLE_LOCALE, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }
}
