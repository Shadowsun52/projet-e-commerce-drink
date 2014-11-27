/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import model.Country;
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
    
    private ArrayList<Country> countries;
            
            
    /**
     * Creates a new instance of CountryMB
     */
    public CountryMB() {
    }

    @PostConstruct
    public void init(){
        setCountries(countryFacade.findAllCountries());
    } 

    /**
     * @return the countries
     */
    public ArrayList<Country> getCountries() {
        return countries;
    }

    /**
     * @param countries the countries to set
     */
    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
