/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.Date;
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
    
    private static final String ERROR_MESSAGE = "min18years";
    private static final long TIME_FOR_ONE_YEAR = 31449600000L;
    private Date birthdate,
                 now;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        birthdate = (Date) value;
        now = new Date();
        if(!eighteenYearsBetweenDate(birthdate, now))
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(ERROR_MESSAGE)));
    }
    
    private boolean eighteenYearsBetweenDate(Date deb, Date end){
        long diffDates = (end.getTime() - deb.getTime())/TIME_FOR_ONE_YEAR;
        return diffDates >= 18.0;
    }
}
