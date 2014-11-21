/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converterPackage;

import entityBeans.Country;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionBeansFacade.CountryFacadeLocal;

/**
 *
 * @author Alexandre
 */
@FacesConverter(forClass=Country.class)
public class CountryConverter implements javax.faces.convert.Converter{
    CountryFacadeLocal countryFacade = lookupCountryFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return countryFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Country) value).getIdcountry());
    }

    private CountryFacadeLocal lookupCountryFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CountryFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/CountryFacade!sessionBeansFacade.CountryFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
