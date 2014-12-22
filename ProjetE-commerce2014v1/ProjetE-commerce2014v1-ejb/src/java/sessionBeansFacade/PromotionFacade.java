/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangPromotion;
import entityBeans.LangPromotionPK;
import entityBeans.Promotion;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import model.Language;

/**
 *
 * @author Alexandre
 */
@Stateless
public class PromotionFacade extends AbstractFacade<Promotion> implements PromotionFacadeLocal {
    @EJB
    private CategoryFacadeLocal categoryFacade;
    @EJB
    private DrinkFacadeLocal drinkFacade;
    @EJB
    private LanguageFacadeLocal languageFacade;
    
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PromotionFacade() {
        super(Promotion.class);
    }

//<editor-fold defaultstate="collapsed" desc="converter">
    @Override
    public model.Promotion converterToModel(Promotion entity) {
        model.Promotion promotion = new model.Promotion(entity.getIdpromotion(), 
                entity.getDatestart(), entity.getDateend(), 
                entity.getCodepromo(), entity.getPromoUnique(), 
                entity.getTypediscount(), entity.getPercentagediscount(), 
                entity.getAmountdiscount(), entity.getMinquantity(), 
                (entity.getIdcategory() == null) ? null : 
                        categoryFacade.converterToModel(entity.getIdcategory()), 
                (entity.getIddrink() == null) ? null :
                        drinkFacade.converterToModel(entity.getIddrink()));
        getMessages(new ArrayList(entity.getLangPromotionCollection()), promotion);
        return promotion;
    }

    private void getMessages(ArrayList<LangPromotion> langsPromo,
            model.Promotion promotion){
        langsPromo.stream().forEach((langPromo) ->{
            promotion.addMessage(languageFacade.converterToModel(
                    langPromo.getLanguage()), langPromo.getMessage());
        });
    }
    
    @Override
    public Promotion converterToEntity(model.Promotion promotion) {
        Promotion entity = new Promotion(promotion.getId(), 
                promotion.getDateStart(), promotion.getDateEnd(), 
                promotion.isPromo_unique(), promotion.getTypeDiscount());
        entity = fillPromotionEntity(entity, promotion);
        return entity;
    }
    
    private Promotion fillPromotionEntity(Promotion entity, model.Promotion promotion){
        entity.setAmountdiscount(promotion.getAmountDiscount());
        entity.setCodepromo(promotion.getCodePromo());
        entity.setIdcategory((promotion.getCategory() == null) ? null :
                categoryFacade.converterToEntity(promotion.getCategory()));
        entity.setIddrink((promotion.getDrink() == null) ? null : 
                drinkFacade.converterToEntity(promotion.getDrink()));
        entity.setMinquantity(promotion.getMinQuantity());
        entity.setPercentagediscount(promotion.getMinQuantity());
        entity.setLangPromotionCollection(
                getLangsPromo(promotion.getHashMessage(), promotion.getId()));
        return entity;
    }
    
    private ArrayList<LangPromotion> getLangsPromo(
            HashMap<Language,String> hashMessage, Integer id){
        ArrayList<LangPromotion> langsPromo = new ArrayList<>();
        hashMessage.forEach((language,message)->{
            langsPromo.add(new LangPromotion(
                    new LangPromotionPK(language.getId(), id), message));
        });
        return langsPromo;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public ArrayList<model.Promotion> findAllPromotions() {
        ArrayList<model.Promotion> promotions = new ArrayList<>();
        try {
            Query query = em.createNamedQuery("Promotion.findAllActif");
            query.setParameter("now", new Date(), TemporalType.DATE);
            query.getResultList().stream().forEach((promotion)->{
                promotions.add(converterToModel((Promotion)promotion));
            });
            return promotions;
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public model.Promotion findPromotion(Object id) {
        return converterToModel(find(id));
    }
    
    @Override
    public model.Promotion findByCodePromo(String codePromo) {
        Query query = em.createNamedQuery("Promotion.findByCodepromo");
        query.setParameter("codepromo", codePromo);
        query.setParameter("now", new Date(), TemporalType.DATE);
        ArrayList<Promotion> promotion = new ArrayList<>(query.getResultList());
        if(promotion.size() > 0)
            return converterToModel(promotion.get(0));
        else
            return null;
       
    }
//</editor-fold>
}
