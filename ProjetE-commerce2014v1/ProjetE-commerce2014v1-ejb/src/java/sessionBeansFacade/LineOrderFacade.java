/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LineOrder;
import entityBeans.LineOrderPK;
import entityBeans.OrderTable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexandre
 */
@Stateless
public class LineOrderFacade extends AbstractFacade<LineOrder> implements LineOrderFacadeLocal {
    @EJB
    private DrinkFacadeLocal drinkFacade;
    
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineOrderFacade() {
        super(LineOrder.class);
    }

//<editor-fold defaultstate="collapsed" desc="converter">
    @Override
    public model.LineOrder converterToModel(LineOrder entity) {
        return new model.LineOrder(
                drinkFacade.converterToModel(entity.getDrink()), 
                entity.getQuantity(), entity.getPrice().doubleValue());
    }

    @Override
    public LineOrder converterToEntity(model.LineOrder line, OrderTable order) {
        LineOrder entity = new LineOrder(new LineOrderPK(line.getDrink().getId(), 
                order.getNumorder()), line.getQuantity(), 
                new BigDecimal(line.getPrice()).setScale(5,RoundingMode.HALF_UP));
        entity.setDrink(drinkFacade.converterToEntity(line.getDrink()));
        entity.setOrderTable(order);
        return entity;
    }
//</editor-fold>
    
    @Override
    public ArrayList<model.LineOrder> findByOrder(Integer numOrder) {
        Query query;
        query=em.createNamedQuery("LineOrder.findByNumorder");
        query.setParameter("numorder", numOrder);
        return new ArrayList(query.getResultList());
    }
    
}
