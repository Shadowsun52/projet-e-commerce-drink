/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converterPackage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Alexandre
 */
@FacesConverter("converterInteger")
public class ConverterInteger implements javax.faces.convert.Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Integer.valueOf(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(value);
    }
    
}
