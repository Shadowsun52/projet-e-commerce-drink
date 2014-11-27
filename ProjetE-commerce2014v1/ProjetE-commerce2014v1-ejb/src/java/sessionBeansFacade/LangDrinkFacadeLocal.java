/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangDrink;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LangDrinkFacadeLocal {

    void create(LangDrink langDrink);

    void edit(LangDrink langDrink);

    void remove(LangDrink langDrink);

    LangDrink find(Object id);

    List<LangDrink> findAll();

    List<LangDrink> findRange(int[] range);

    int count();
    
}
