/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Drink;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface DrinkFacadeLocal {

    void create(Drink drink);

    void edit(Drink drink);

    void remove(Drink drink);

    Drink find(Object id);

    List<Drink> findAll();

    List<Drink> findRange(int[] range);
    
    public ArrayList<Drink> getDrinkByCateg(Integer idCateg);

    int count();
    
    public model.Drink converterToModel(Drink entity);
    
    public ArrayList<model.Drink> findAllDrinks();
    
    public ArrayList<model.Drink> findDrinksbyCateg(Integer idCateg);
    
    public ArrayList<model.Drink> findDrinks(Integer idCateg, model.Type type, 
            int lowValue, int highValue, double lowPercentage, double highPercentage);
    
}
