/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

/**
 *
 * @author Alexandre
 */
public abstract class ValidatorMultiFields implements Validator{
    
    protected String getField(FacesContext context, UIComponent component, String nameComponent){
        UIInput input = (UIInput) component.findComponent(nameComponent);
        String value = (String)input.getSubmittedValue();
        if(value == null)
            value = (String)input.getValue();
        return value;
    }
}
