/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alexandre
 */
@FacesValidator("validatorEmail")
public class ValidatorEmail implements Validator{
    private String email;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        email = (String)value; 
        if(!Pattern.matches("^\\S{4,}@\\D{3,}[.]\\D{2,4}$",email))
        {
            Locale locale = context.getViewRoot().getLocale();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(
                    "languagePackage.lang", locale);
            throw new ValidatorException(
                    new FacesMessage(resourceBundle.getString("emailNotCorrect")));
        }
        
    }
    
    
}
