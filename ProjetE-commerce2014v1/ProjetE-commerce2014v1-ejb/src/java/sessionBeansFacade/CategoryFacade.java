/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Category;
import entityBeans.LangCat;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal {
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;
    private final LanguageFacade languageFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
        languageFacade = new LanguageFacade();
    }
    
    @Override
    public model.Category findCategory(Object id){
        Category entityCat = find(id);
        return converterToModel(entityCat);
    }

    @Override
    public ArrayList<model.Category> findAllCategories() {
        ArrayList<model.Category> listCategories = new ArrayList();
        findAll().stream().forEach((category)-> {
            listCategories.add(converterToModel(category));
        });
        return listCategories;
    }
    
    @Override
    public model.Category converterToModel(Category entity)
    {
        model.Category category = new model.Category(entity.getIdcategory(), 
                entity.getDaterequired());
        for (LangCat langCat : entity.getLangCatCollection()) {
            category.addLabel(langCat.getLanguage().getShortlabel(),
                    langCat.getLabel());
        }
        return category;
    }
}