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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import model.Drink;
import sessionBeansFacade.DrinkFacadeLocal;

/**
 *
 * @author Alex
 */
@Named(value = "drinksMB")
@ViewScoped
public class DrinksMB implements Serializable{
    @EJB
    private DrinkFacadeLocal drinkFacade;
    
    private ArrayList<Drink> listDrink;

    
    /**
     * Creates a new instance of DrinksMB
     */
    public DrinksMB() {    
    }
    
    @PostConstruct
    public void init(){
        listDrink=findDrinksbyCateg();
        System.out.println("machin");
    }
    /*public List<Drink> findDrinks() {       
        return drinkFacade.findAllDrinks();
    }*/
    
    public ArrayList<Drink> findDrinksbyCateg()
    {     
        FacesContext context=FacesContext.getCurrentInstance();
        Map<String,String> params = context.getExternalContext().getRequestParameterMap();
        return drinkFacade.findDrinksbyCateg(Integer.valueOf(params.get("idCat")));
    }
    
    public ArrayList<Drink> getListDrink() {
        return listDrink;
    }

    public void setListDrink(ArrayList<Drink> listDrink) {
        this.listDrink = listDrink;
    }
}
