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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import model.Customer;
import model.DeliveryMode;
import model.Drink;
import model.Language;

/**
 *
 * @author Alexandre
 */
@Named(value = "caddyMB")
@SessionScoped
public class CaddyMB implements Serializable {

    private HashMap<Drink,Integer> caddy;
    private DeliveryMode delModChosen;
    /**
     * Creates a new instance of CaddyMB
     */
    public CaddyMB() {
        caddy = new HashMap();
        //valeur test
        Drink d = new Drink(10, 2.1, .33, (short)6, null);
        d.addLabel(new Language(10, "Francais", "", "fr"), "Orval");
        d.addLabel(new Language(11, "English","", "en"), "Orval");
        caddy.put(d, 6);
        
        Drink d2 = new Drink(11, 1.7, .33, (short)5, null);
        d2.addLabel(new Language(10, "Francais", "", "fr"), "Leffe");
        d2.addLabel(new Language(11, "English","", "en"), "Leffe");
        caddy.put(d2, 24);           
    }

//<editor-fold defaultstate="collapsed" desc="Management Caddy">
    public void addDrink(Drink drink, int quantity){
        if(caddy.containsKey(drink))
            caddy.put(drink, caddy.get(drink) + quantity);
        else
            caddy.put(drink, quantity);
    }
    
    public void deleteDrink(Drink drink){
        caddy.remove(drink);
    }
    
    public boolean caddyIsEmpty(){
        return caddy.isEmpty();
    }
    
    public void clearCaddy(){
        caddy.clear();
    }
    
    public double sumline(Drink drink){
        return drink.getCurrentPrice()*caddy.get(drink);
    }
    
    public double sumCaddy(){
        double sum = 0.;
        for (Map.Entry<Drink, Integer> drink : caddy.entrySet()) {
            sum += sumline(drink.getKey());
        }
        return sum;
    }
    
    public double tvaCaddy(Customer customer){
        return sumCaddy()*customer.getAddress().getCountry().getTva()/100;
    }
    
    public double sumCaddyWithTva(Customer customer){
        return sumCaddy() + tvaCaddy(customer);
    }
//</editor-fold>   
    
//<editor-fold defaultstate="collapsed" desc="getter and setter">
    /**
     * @return the caddy
     */
    public HashMap<Drink,Integer> getCaddy() {
        return caddy;
    }

    /**
     * @param caddy the caddy to set
     */
    public void setCaddy(HashMap<Drink,Integer> caddy) {
        this.caddy = caddy;
    }
    
    public List<Entry<Drink,Integer>> getListCaddy(){
        return new ArrayList(caddy.entrySet());
    }
    
    /**
     * @return the delModChosen
     */
    public DeliveryMode getDelModChosen() {
        return delModChosen;
    }

    /**
     * @param delModChosen the delModChosen to set
     */
    public void setDelModChosen(DeliveryMode delModChosen) {
        this.delModChosen = delModChosen;
    }
//</editor-fold>   

}
