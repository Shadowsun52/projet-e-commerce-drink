/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import entityBeans.Country;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import sessionBeansFacade.CountryFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "countryMB")
@ApplicationScoped
public class CountryMB {
    @EJB
    private CountryFacadeLocal countryFacade;
    /**
     * Creates a new instance of CountryMB
     */
    public CountryMB() {
    }
    
    public ArrayList<Country> getAllCountries() {
        return new ArrayList(countryFacade.findAll());
    }
}
