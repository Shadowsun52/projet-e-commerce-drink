/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import entityBeans.Country;
import entityBeans.Locality;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import sessionBeansFacade.CountryFacadeLocal;
import sessionBeansFacade.LocalityFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "localityMB")
@SessionScoped
public class LocalityMB implements Serializable {
    @EJB
    private CountryFacadeLocal countryFacade;
    @EJB
    private LocalityFacadeLocal localityFacade;

    private String idCountryChosen;
    /**
     * Creates a new instance of LocalityMB
     */
    public LocalityMB() {
        idCountryChosen = "10";
    }
    
    public ArrayList<Country> getAllCountries() {
        return new ArrayList(countryFacade.findAll());
    }

    /**
     * @return the idCountryChosen
     */
    public String getIdCountryChosen() {
        return idCountryChosen;
    }

    /**
     * @param idCountryChosen the idCountryChosen to set
     */
    public void setIdCountryChosen(String idCountryChosen) {
        this.idCountryChosen = idCountryChosen;
    }
    
    public ArrayList<Locality> getAllLocality() {
        return new ArrayList(localityFacade.findAll());
    }

    public ArrayList<Locality> getLocalityForCountry() {
        Country countryChosen = countryFacade.find(new Integer(idCountryChosen));
        return new ArrayList(countryChosen.getLocalityCollection());
    }
}
