/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Language;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alexandre
 */
@Stateless
public class LanguageFacade extends AbstractFacade<Language> implements LanguageFacadeLocal {
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LanguageFacade() {
        super(Language.class);
    }
    
    public Language findByShortLabel(String shortlabel) {
        Query query;
        query = em.createNamedQuery("Language.findByShortlabel");
        query.setParameter("shortlabel", shortlabel);
        return (Language) query.getSingleResult();
    }
}
