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
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alexandre
 */
@FacesValidator("validatorConfirmEmail")
public class ValidatorConfirmEmail extends ValidatorMultiFields{

    private String email,
                   confirmEmail;
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        confirmEmail = (String)value;
        email = getField(context, component, "inputEmail");
        if(!email.equals(confirmEmail)){
            Locale locale = context.getViewRoot().getLocale();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(
                    "languagePackage.lang", locale);
            throw new ValidatorException(
                    new FacesMessage(resourceBundle.getString("emailsNotSame")));
        }
    }
}
