/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Drink;
import entityBeans.LangDrink;
import entityBeans.LangDrinkPK;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
            drink.addLabel(languageFacade.converterToModel(langDrink.getLanguage()), langDrink.getLabel());
        }
        return drink;
    }

    @Override
    public Drink converterToEntity(model.Drink drink){
        Drink entity = new Drink(drink.getId(), 
                new BigDecimal(drink.getCurrentPrice()), 
                new BigDecimal(drink.getCapacity()), 
                drink.getPercentageAlcohol());
        entity.setLangDrinkCollection(getAllInfo(drink.getHashLabel(), 
                drink.getId()));
        return entity;
    }
    
    private Collection<LangDrink> getAllInfo(
            HashMap<Language,String> hashLabel, Integer id) {
        Collection<LangDrink> labels = new ArrayList();
        for (Map.Entry<Language, String> label : hashLabel.entrySet()) {
            labels.add(new LangDrink(new LangDrinkPK(id, label.getKey().getId()),
                    label.getValue(), null));
        }
        return labels;
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
    
}
