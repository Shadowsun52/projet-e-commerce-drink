/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangType;
import entityBeans.Type;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class TypeFacade extends AbstractFacade<Type> implements TypeFacadeLocal {
    @EJB
    private LanguageFacadeLocal languageFacade;
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeFacade() {
        super(Type.class);
    }
    
    @Override
    public model.Type converterToModel(Type entity) {
        model.Type type = new model.Type(entity.getIdtype(), 
                new model.Category(entity.getIdcategory().getIdcategory(), 
                        entity.getIdcategory().getDaterequired()));
        for (LangType langType : entity.getLangTypeCollection()) {
            type.addLabel(languageFacade.converterToModel(langType.getLanguage()),langType.getLabel());
        }
        return type; 
    }
    
    @Override
    public ArrayList<entityBeans.Type> findEntityByCateg(int idCateg){
        Query query;
        query=em.createNamedQuery("Type.findByCateg");
        query.setParameter("idcateg", idCateg);
        
        return new ArrayList(query.getResultList());
    }
    
    @Override
    public ArrayList<model.Type> findByCateg(int idCateg){ 
        
        ArrayList<model.Type> arrayModelType=new ArrayList();
        
        findEntityByCateg(idCateg).stream().forEach((type) -> {
            arrayModelType.add(converterToModel(type));
        });
        
        return arrayModelType;
    }
    
    @Override
    public model.Type findType(Object id){
        return converterToModel(find(id));
    }
    
}
