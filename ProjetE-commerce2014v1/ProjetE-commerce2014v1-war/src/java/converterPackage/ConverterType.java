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
import model.Type;
import sessionBeansFacade.TypeFacadeLocal;

/**
 *
 * @author Alex
 */
@FacesConverter(forClass=Type.class)
public class ConverterType implements javax.faces.convert.Converter{
    TypeFacadeLocal typeFacade = lookupTypeFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return typeFacade.findType(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Type)value).getId());
    }

    private TypeFacadeLocal lookupTypeFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (TypeFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/TypeFacade!sessionBeansFacade.TypeFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}
