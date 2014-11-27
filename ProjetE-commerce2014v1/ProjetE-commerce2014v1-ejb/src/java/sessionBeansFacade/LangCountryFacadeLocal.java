/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangCountry;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LangCountryFacadeLocal {

    void create(LangCountry langCountry);

    void edit(LangCountry langCountry);

    void remove(LangCountry langCountry);

    LangCountry find(Object id);

    List<LangCountry> findAll();

    List<LangCountry> findRange(int[] range);

    int count();
    
}
