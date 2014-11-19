/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionBeansFacade.CustomerFacadeLocal;

/**
 *
 * @author Alexandre
 */
@FacesValidator("validatorEmail")
public class ValidatorEmail  implements Validator{
    private static final String BUNDLE_LOCALE = "languagePackage.lang",
                                EMAIL_EXIST = "emailExist",
                                ERROR_EMAIL = "emailNotCorrect";
    
    CustomerFacadeLocal customerFacade = lookupCustomerFacadeLocal();
    private String email;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        email = (String) value;
        if(customerFacade.findByEmail(email) != null)
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(EMAIL_EXIST)));
            
        if(!Pattern.matches("^[a-zA-Z0-9._-]{2,}@[a-z0-9._-]{2,}\\.[a-z]{2,4}$",email))
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(ERROR_EMAIL)));
    }

    private CustomerFacadeLocal lookupCustomerFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CustomerFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/CustomerFacade!sessionBeansFacade.CustomerFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private ResourceBundle linkToBundle(FacesContext context){
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle(BUNDLE_LOCALE, locale);
    }
}
