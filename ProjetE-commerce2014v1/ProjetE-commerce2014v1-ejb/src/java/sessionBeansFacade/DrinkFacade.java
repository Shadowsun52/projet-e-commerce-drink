/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Drink;
import entityBeans.LangDrink;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Language;

/**
 *
 * @author Alex
 */
@Stateless
public class DrinkFacade extends AbstractFacade<Drink> implements DrinkFacadeLocal {
    @EJB
    private LanguageFacadeLocal languageFacade;
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DrinkFacade() {
        super(Drink.class);
    }
    
    @Override
    public ArrayList<Drink> getDrinkByCateg(Integer idCateg) {
        Query query;
        query=em.createNamedQuery("Drink.findByCateg");
        query.setParameter("idcateg", idCateg);
        
        return new ArrayList(query.getResultList());
    }

    @Override
    public model.Drink converterToModel(Drink entity) {
        model.Drink drink = new model.Drink(entity.getIddrink(), entity.getCurrentprice().doubleValue(), 
                entity.getCapacity().doubleValue(), entity.getPercentagealcohol(), entity.getDatebottling());
        for(LangDrink langDrink : entity.getLangDrinkCollection()){
            drink.addText(languageFacade.converterToModel(langDrink.getLanguage()), 
                    langDrink.getLabel(), langDrink.getDescription());
        }
        return drink;
    }

    @Override
    public ArrayList<model.Drink> findAllDrinks() {
        ArrayList<model.Drink> listDrink = new ArrayList();
        findAll().stream().forEach((address) -> {
            listDrink.add(converterToModel(address));
        });
        return listDrink;
    }

    @Override
    public ArrayList<model.Drink> findDrinksbyCateg(Integer idCateg) {
        ArrayList<model.Drink> listDrink = new ArrayList();
        getDrinkByCateg(idCateg).stream().forEach((drink) -> {
            listDrink.add(converterToModel(drink));
        });
        return listDrink;
    }
    
    @Override
    public ArrayList<model.Drink> findDrinks(Integer idCateg, model.Type type, 
            int lowValue, int highValue, double lowPercentage, double highPercentage){
        Query query;
        query=em.createNamedQuery("Drink.findDrinks");
        query.setParameter("idcateg", idCateg);
        query.setParameter("idtype", type.getId());
        query.setParameter("lowvalue", lowValue);
        query.setParameter("highvalue", highValue);
        query.setParameter("lowpercentage", lowPercentage);
        query.setParameter("highpercentage", highPercentage);
        ArrayList<entityBeans.Drink> listDrink = new ArrayList(query.getResultList());

        ArrayList<model.Drink> listModelDrink = new ArrayList();
        listDrink.stream().forEach((drink) -> {
            listModelDrink.add(converterToModel(drink));
        });
        return listModelDrink;
    }
    
    @Override
    public ArrayList<model.Drink> findDrinksNoType(Integer idCateg, int lowValue, 
            int highValue, double lowPercentage, double highPercentage){
        Query query;
        query=em.createNamedQuery("Drink.findDrinksNoType");
        query.setParameter("idcateg", idCateg);
        query.setParameter("lowvalue", lowValue);
        query.setParameter("highvalue", highValue);
        query.setParameter("lowpercentage", lowPercentage);
        query.setParameter("highpercentage", highPercentage);
        ArrayList<entityBeans.Drink> listDrink = new ArrayList(query.getResultList());

        ArrayList<model.Drink> listModelDrink = new ArrayList();
        listDrink.stream().forEach((drink) -> {
            listModelDrink.add(converterToModel(drink));
        });
        return listModelDrink;
    }
    
    @Override
    public model.Drink findSingleDrink(Integer idDrink)
    {
        return converterToModel(find(idDrink));
    }
    
    @Override
    public ArrayList<model.Drink> findSearchedDrink(String stringSearched, Language lang){
        Query query;
        query=em.createNamedQuery("Drink.findAll");
        
        ArrayList<model.Drink> listModelDrink = new ArrayList();
        query.getResultList().stream().forEach((drink) -> {
            listModelDrink.add(converterToModel((Drink)drink));
        });
        
        ArrayList<model.Drink> listSearchedDrink = new ArrayList();
        listModelDrink.stream().forEach((drink) -> {
            if(Pattern.matches(("^.*"+stringSearched+".*$").toUpperCase(), drink.getText(lang).getLabel().toUpperCase()))
                listSearchedDrink.add(drink);
        });
        
        return listSearchedDrink;
    }
}
