/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.GregorianCalendar;
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
@FacesValidator("validatorBirthdate")
public class ValidatorBirthdate extends ValidatorMultiFields{
    
    private static final String DAY_FIELD = "composantDay",
                                MONTH_FIELD = "composantMonth",
                                YEAR_FIELD = "composantYear",
                                ERROR_MESSAGE = "emailsNotSame";
    
    private Integer day,
                    month,
                    year;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        readingValue(context, component);
        if(fieldsAreEmpty())
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(ERROR_MESSAGE)));
        
        GregorianCalendar birthdate = new GregorianCalendar(day, month, year);
        year = 0;
    }
    
    private void readingValue(FacesContext context, UIComponent component) {
        day = Integer.parseInt(getField(context, component, DAY_FIELD));
        month = Integer.parseInt(getField(context, component, MONTH_FIELD));
        year = Integer.parseInt(getField(context, component, YEAR_FIELD));
    }
    
    private Boolean fieldsAreEmpty() {
        return day == null || month == null || year == null;
    }
}
