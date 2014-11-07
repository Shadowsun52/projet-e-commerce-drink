/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import entityBeans.Category;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import sessionBeansFacade.CategoryFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "categoryMB")
@ApplicationScoped
public class CategoriesMB {
    @EJB
    private CategoryFacadeLocal categoryFacade;
    
    /**
     * Creates a new instance of ManagedBeanCategory
     */
    public CategoriesMB() {
    }
    
    public ArrayList<Category> getAllCategory() {
       return new ArrayList(categoryFacade.findAll());
    }
}
