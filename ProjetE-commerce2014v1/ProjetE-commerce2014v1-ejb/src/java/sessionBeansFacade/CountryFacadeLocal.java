/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Country;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface CountryFacadeLocal {

    void create(Country country);

    void edit(Country country);

    void remove(Country country);

    Country find(Object id);

    List<Country> findAll();

    List<Country> findRange(int[] range);

    int count();
    
    public model.Country converterToModel(Country entity);
    
    public Country converterToEntity(model.Country country);
    
    public model.Country findCountry(Object id);
    
    public ArrayList<model.Country> findAllCountries();
}
