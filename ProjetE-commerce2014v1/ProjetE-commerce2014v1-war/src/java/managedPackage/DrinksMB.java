/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.Serializable;
import java.util.ArrayList;
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
    private int lowPrice=0;
    private int highPrice=25;
    private int lowPercentage=0;
    private int highPercentage=40;
    private int singleDrink;
    private int nbDrinks;
    /**
     * Creates a new instance of DrinksMB
     */
    public DrinksMB() {    
    }
    
    public ArrayList<Drink> findDrinksbyCateg()
    {     
        return drinkFacade.findDrinksbyCateg(idCat);
    }
    
    public void findDrinks()
    {   
        if(typeChosen!=null)
            listDrink = drinkFacade.findDrinks(idCat,typeChosen,lowPrice,
                highPrice, lowPercentage, highPercentage);
        else
            listDrink = drinkFacade.findDrinksNoType(idCat,lowPrice,
                highPrice, lowPercentage, highPercentage);     
    }
    
    public Drink findSingleDrink()
    {
        return drinkFacade.findSingleDrink(singleDrink);
    }
    
    public void resetCriterias(){
        typeChosen=null;
        lowPrice=0;
        highPrice=25;
        lowPercentage=0;
        highPercentage=40;
    }
    
    public double processedPrice(double priceDrink){
        return nbDrinks*priceDrink;
    }
    
    public ArrayList<Drink> getListDrink() {
        findDrinks();
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
    
    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

    public int getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(int highPrice) {
        this.highPrice = highPrice;
    }
    
    public int getLowPercentage() {
        return lowPercentage;
    }

    public void setLowPercentage(int lowPercentage) {
        this.lowPercentage = lowPercentage;
    }

    public int getHighPercentage() {
        return highPercentage;
    }

    public void setHighPercentage(int highPercentage) {
        this.highPercentage = highPercentage;
    }
    
    public int getSingleDrink() {
        return singleDrink;
    }

    public void setSingleDrink(int singleDrink) {
        this.singleDrink = singleDrink;
    }
    
    public int getNbDrinks() {
        return nbDrinks;
    }

    public void setNbDrinks(int nbDrinks) {
        this.nbDrinks = nbDrinks;
    }
}
