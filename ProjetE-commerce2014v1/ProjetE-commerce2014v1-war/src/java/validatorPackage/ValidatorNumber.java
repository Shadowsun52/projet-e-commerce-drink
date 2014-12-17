/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.math.BigDecimal;
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
@FacesValidator("validatorNumber")
public class ValidatorNumber implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try{
            BigDecimal nb = (BigDecimal)value;
            if( nb.intValue() <= 0)
                throw new ValidatorException(
                    new FacesMessage("error"));
        }
        catch(Exception e)
        {
            throw new ValidatorException(
                    new FacesMessage("error"));
        }
    }
    
}
