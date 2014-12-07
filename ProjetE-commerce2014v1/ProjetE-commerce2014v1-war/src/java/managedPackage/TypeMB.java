/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Type;
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
     * Creates a ne w instance of TypeMB
     */
    public TypeMB() {
    }
    
    public ArrayList<Type> getTypeByCateg(){
        FacesContext context = FacesContext.getCurrentInstance();
        DrinksMB dm = (DrinksMB) context
                .getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), 
                        "#{drinksMB}", 
                        DrinksMB.class)
                .getValue(context.getELContext());
        return typeFacade.findByCateg(dm.getIdCat());
    }
    
}
