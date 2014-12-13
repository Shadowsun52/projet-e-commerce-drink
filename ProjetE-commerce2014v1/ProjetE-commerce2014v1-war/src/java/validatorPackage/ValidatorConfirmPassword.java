/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alexandre
 */
@FacesValidator("validatorConfirmPassword")
public class ValidatorConfirmPassword extends ValidatorMultiFields{

    private static final String PASSWORD_FIELD = "composantPassword",
                                ERROR_MESSAGE = "passwordsNotSame";
    
    private String password,
                   confirmPassword;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        confirmPassword = (String)value;
        password = getField(component, PASSWORD_FIELD);
        if(!password.equals(confirmPassword)){
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(ERROR_MESSAGE)));
        }
    }
    
}
