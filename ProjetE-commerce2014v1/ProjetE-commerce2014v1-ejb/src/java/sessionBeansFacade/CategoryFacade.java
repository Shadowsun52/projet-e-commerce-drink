/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Category;
import entityBeans.LangCat;
import entityBeans.LangCatPK;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Language;

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
            category.addLabel(languageFacade.converterToModel(langCat.getLanguage()),
                    langCat.getLabel());
        }
        return category;
    }

    @Override
    public Category converterToEntity(model.Category category) {
        Category entity = new Category(category.getId(), 
                category.isDateRequired());
        entity.setLangCatCollection(getLangsCat(
                category.getHashLabel(), category.getId()));
        return entity;
    }
    
    private ArrayList<LangCat> getLangsCat(HashMap<Language,String> labels, Integer id){
        ArrayList<LangCat> langsCat = new ArrayList<>();
        labels.forEach((language,label)->{
            langsCat.add(new LangCat(new LangCatPK(id, language.getId()), label));
        });
        return langsCat;
    }
}