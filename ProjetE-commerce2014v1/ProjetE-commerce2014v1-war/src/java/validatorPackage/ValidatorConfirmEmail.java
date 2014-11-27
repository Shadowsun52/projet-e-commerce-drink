/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alexandre
 */
@FacesValidator("validatorConfirmEmail")
public class ValidatorConfirmEmail extends ValidatorMultiFields{

    private static final String EMAIL_FIELD = "composantEmail",
                                ERROR_MESSAGE = "emailsNotSame";
    
    private String email,
                   confirmEmail;
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        confirmEmail = (String)value;
        email = getField(context, component, EMAIL_FIELD);
        if(!email.equals(confirmEmail)){
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(ERROR_MESSAGE)));
        }
    }
}