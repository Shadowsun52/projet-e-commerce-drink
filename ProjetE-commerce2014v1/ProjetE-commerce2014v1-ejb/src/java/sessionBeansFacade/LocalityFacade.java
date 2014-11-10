/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Locality;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexandre
 */
@Stateless
public class LocalityFacade extends AbstractFacade<Locality> implements LocalityFacadeLocal {
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalityFacade() {
        super(Locality.class);
    }
    
    @Override
    public List<Locality> findByCountry(Integer idCountry) {
        Query query;
        query = em.createNamedQuery("Country.findByIdcountry");
        query.setParameter("idcountry", idCountry);
        return query.getResultList();
    }
}
