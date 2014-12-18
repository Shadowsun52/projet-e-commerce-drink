/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alex
 */
@FacesValidator("validatorPhone")
public class ValidatorPhone implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        try{
            String numPhone=(String)value;
            
            if(!numPhone.equals(""))
                if(!Pattern.matches("^((0|\\+32 ?)|((0|\\+33 ?)\\d[-.\\/ ]?))\\d{2}([-.\\/ ]?\\d{2}){3}+$", numPhone))
                    throw new ValidatorException(
                        new FacesMessage("error"));
        }
        catch(Exception e){
            throw new ValidatorException(
                    new FacesMessage("error"));
        }
    }
}
