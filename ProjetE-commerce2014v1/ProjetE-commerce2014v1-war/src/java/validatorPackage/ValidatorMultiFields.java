/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

/**
 *
 * @author Alexandre
 */
public abstract class ValidatorMultiFields implements Validator{
    private static final String BUNDLE_LOCALE = "languagePackage.lang";
    
    protected String getField(FacesContext context, UIComponent component, String nameComponent){
            UIInput componentField  =(UIInput) component.getAttributes().get(nameComponent);
            return (String) componentField.getValue();
    }
    
    protected ResourceBundle linkToBundle(FacesContext context){
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle(BUNDLE_LOCALE, locale);
    }
}
