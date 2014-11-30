/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sessionBeansFacade.TypeFacadeLocal;

/**
 *
 * @author Alex
 */
@Named(value = "typeMB")
@ViewScoped
public class TypeMB implements Serializable{
    @EJB
    private TypeFacadeLocal typeFacade;

    /**
     * Creates a new instance of TypeMB
     */
    public TypeMB() {
    }
    
    public ArrayList<model.Type> getTypeByCateg(){
        FacesContext context=FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        return typeFacade.findByCateg(Integer.valueOf(params.get("idCat")));
    }
    
}
