/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Drink;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class DrinkFacade extends AbstractFacade<Drink> implements DrinkFacadeLocal {
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
        return new model.Drink(entity.getIddrink(), entity.getCurrentprice().doubleValue(), 
                entity.getCapacity().doubleValue(), entity.getPercentagealcohol(), entity.getDatebottling());
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
    
}
