/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import entityBeans.OrderTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexandre
 */
@Stateless
public class OrderTableFacade extends AbstractFacade<OrderTable> implements OrderTableFacadeLocal {
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderTableFacade() {
        super(OrderTable.class);
    }

//<editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public boolean OrderUsingAddress(Address address) {
        try {
            Query query = em.createNamedQuery("OrderTable.findByDelAddress");
            query.setParameter("delAddress", address);
            query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
//</editor-fold>
}
