/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Language;
import java.util.ArrayList;
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
    
    @Override
    public model.Language findByShortLabel(String shortlabel) {
        Query query;
        query = em.createNamedQuery("Language.findByShortlabel");
        query.setParameter("shortlabel", shortlabel);
        return converterToModel((Language) query.getSingleResult());
    }

    @Override
    public model.Language findLanguage(Object id) {
        return converterToModel(find(id));
    }

    @Override
    public ArrayList<model.Language> findAllLanguages() {
        ArrayList<model.Language> languages = new ArrayList();
        findAll().stream().forEach((entity) -> {
            languages.add(converterToModel(entity));
        });
        return languages;
    }
    
    private model.Language converterToModel(Language entity) {
        return new model.Language(entity.getIdlanguage(), entity.getLabel(), 
                entity.getSlogan(), entity.getShortlabel());
    }
}
