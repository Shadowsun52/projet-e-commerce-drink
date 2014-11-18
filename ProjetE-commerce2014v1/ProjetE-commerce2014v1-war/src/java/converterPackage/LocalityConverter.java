/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converterPackage;

import entityBeans.Locality;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionBeansFacade.LocalityFacadeLocal;

/**
 *
 * @author Alexandre
 */
@FacesConverter(forClass=Locality.class)
public class LocalityConverter implements javax.faces.convert.Converter{
    LocalityFacadeLocal localityFacade = lookupLocalityFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return localityFacade.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Locality) value).getIdlocality());
    }

    private LocalityFacadeLocal lookupLocalityFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (LocalityFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/LocalityFacade!sessionBeansFacade.LocalityFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
