/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Drink;
import model.Type;
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
    private model.Type typeChosen;
    private int idCat;
    
    
    /**
     * Creates a new instance of DrinksMB
     */
    public DrinksMB() {    
    }
    
    @PostConstruct
    public void init(){
        System.out.println("loll");
    }
    
    public ArrayList<Drink> findDrinksbyCateg()
    {     
        return drinkFacade.findDrinksbyCateg(idCat);
    }
    
    public ArrayList<Drink> getListDrink() {
        return listDrink;
    }

    public void setListDrink(ArrayList<Drink> listDrink) {
        this.listDrink = listDrink;
    }
    
    public Type getTypeChosen() {
        return typeChosen;
    }

    public void setTypeChosen(Type typeChosen) {
        this.typeChosen = typeChosen;
    }
    
    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
        listDrink=findDrinksbyCateg();
        
    }
}
