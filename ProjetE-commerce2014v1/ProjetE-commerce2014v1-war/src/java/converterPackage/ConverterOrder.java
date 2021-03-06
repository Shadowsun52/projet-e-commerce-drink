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
import model.Order;
import sessionBeansFacade.OrderTableFacadeLocal;

/**
 *
 * @author Alexandre
 */
@FacesConverter(forClass=Order.class)
public class ConverterOrder implements javax.faces.convert.Converter{
    OrderTableFacadeLocal orderTableFacade = lookupOrderTableFacadeLocal();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return orderTableFacade.findOrder(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Order)value).getNumOrder());
    }

    private OrderTableFacadeLocal lookupOrderTableFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (OrderTableFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/OrderTableFacade!sessionBeansFacade.OrderTableFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
