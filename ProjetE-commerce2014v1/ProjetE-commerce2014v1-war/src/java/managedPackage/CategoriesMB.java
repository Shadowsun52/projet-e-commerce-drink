/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import model.Category;
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
    private int idCat; 
    
    private ArrayList<Category> categories;
    /**
     * Creates a new instance of ManagedBeanCategory
     */
    public CategoriesMB() {    
    }

    @PostConstruct
    public void init(){
        categories = categoryFacade.findAllCategories();
    }
    /**
     * @return the categories
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    
    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }
}
