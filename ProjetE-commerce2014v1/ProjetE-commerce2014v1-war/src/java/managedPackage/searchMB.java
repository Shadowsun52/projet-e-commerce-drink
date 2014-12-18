/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import model.Drink;
import model.Language;
import sessionBeansFacade.DrinkFacadeLocal;

/**
 *
 * @author Alex
 */
@Named(value = "searchMB")
@SessionScoped
public class searchMB implements Serializable {
    @EJB
    private DrinkFacadeLocal drinkFacade;
    
    private ArrayList<Drink> listSearchDrink;
    private String stringResearched;

    /**
     * Creates a new instance of searchMB
     */
    public searchMB() {
    }
    
    public String goToSearchDrink(Language lang){
        if(Pattern.matches("^.*o.*$", "chouffe"))
                System.out.println("lol");
        listSearchDrink = drinkFacade.findSearchedDrink(stringResearched, lang);
        
        return "searchPage";
    }
    
    public String getStringResearched() {
        return stringResearched;
    }

    public void setStringResearched(String stringResearched) {
        this.stringResearched = stringResearched;
    }

    public ArrayList<Drink> getListSearchDrink() {
        return listSearchDrink;
    }

    public void setListSearchDrink(ArrayList<Drink> listSearchDrink) {
        this.listSearchDrink = listSearchDrink;
    }
}
