/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import business.MathBusiness;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Drink;
import model.Promotion;
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
    
    private final MathBusiness math = new MathBusiness();
    private ArrayList<Drink> listDrink;
    private model.Type typeChosen;
    private int idCat;
    private int lowPrice=0;
    private int highPrice=25;
    private int lowPercentage=0;
    private int highPercentage=40;
    private short nbDrinks=1;
    private Drink singleDrink;
    private Promotion promotionDrink;
    
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
    
    public double processedPrice(double priceDrink){
        return math.sumWithPromotion(priceDrink, nbDrinks, promotionDrink);
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
    
    public short getNbDrinks() {
        return nbDrinks;
    }

    public void setNbDrinks(short nbDrinks) {
        this.nbDrinks = nbDrinks;
    }
      
    public Drink getSingleDrink() {
        return singleDrink;
    }

    public void setSingleDrink(Drink singleDrink) {
        this.singleDrink = singleDrink;
        FacesContext context = FacesContext.getCurrentInstance();
         PromotionMB promotionMB = (PromotionMB) 
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{promotionMB}", 
                                PromotionMB.class)
                        .getValue(context.getELContext());
         promotionDrink = promotionMB.promotionForDrink(singleDrink);
    }

    /**
     * @return the promotionDrink
     */
    public Promotion getPromotionDrink() {
        return promotionDrink;
    }

    /**
     * @param promotionDrink the promotionDrink to set
     */
    public void setPromotionDrink(Promotion promotionDrink) {
        this.promotionDrink = promotionDrink;
    }
}
