/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converterPackage;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Drink;
import sessionBeansFacade.DrinkFacadeLocal;

/**
 *
 * @author Alex
 */
@FacesConverter(forClass=Drink.class)
public class ConverterDrink implements javax.faces.convert.Converter{
    DrinkFacadeLocal drinkFacade = lookupDrinkFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return drinkFacade.findSingleDrink(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Drink)value).getId());
    }

    private DrinkFacadeLocal lookupDrinkFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (DrinkFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/DrinkFacade!sessionBeansFacade.DrinkFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
