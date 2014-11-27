/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Country;
import entityBeans.LangCountry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre
 */
@Stateless
public class CountryFacade extends AbstractFacade<Country> implements CountryFacadeLocal {
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountryFacade() {
        super(Country.class);
    }
    
    @Override
    public model.Country converterToModel(Country entity){
        model.Country country = new model.Country(entity.getIdcountry(), 
                entity.getTva());      
        for(LangCountry langCountry: entity.getLangCountryCollection()){
            country.addLabel(langCountry.getLanguage().getShortlabel(), 
                    langCountry.getLabel());
        }
        return country;
    }

    @Override
    public Country converterToEntity(model.Country country)
    {
       Country entity = new Country(country.getId(), country.getTva());
       entity.setLangCountryCollection(getAllLabels(country.getHashLabel()));
       return entity;
    }         
            
    private Collection<LangCountry> getAllLabels(HashMap<String,String> hashLabel) {
        Collection<LangCountry> labels = new ArrayList();
        for (Map.Entry<String, String> label : hashLabel.entrySet()) {
            labels.add(new LangCountry(null,label.getValue()));
        }
        return labels;
    }
    
    @Override
    public model.Country findCountry(Object id) {
        return converterToModel(find(id));
    }

    @Override
    public ArrayList<model.Country> findAllCountries() {
        ArrayList<model.Country> listCountries = new ArrayList();
        findAll().stream().forEach((country)-> {
            listCountries.add(converterToModel(country));
        });
        return listCountries;
    }
}
