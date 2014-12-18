/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validatorPackage;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import managedPackage.CaddyMB;
import managedPackage.InternationalizationManaged;
import model.Drink;
import model.Language;
import model.Promotion;
import sessionBeansFacade.PromotionFacadeLocal;

/**
 *
 * @author Alexandre
 */
@FacesValidator("validatorCodePromo")
public class ValidatorCodePromo implements Validator{
    private static final String BUNDLE_LOCALE = "languagePackage.lang";
    private static final String CODE_PROMO_NOT_EXIT ="codePromoNotExit";
    private static final String DRINK_NOT_PRESENT ="promoDrinkNotPresent";
    private static final String NOT_ENOUGH_DRINK = "promoNotEnoughDrink";
    private static final String ALREADY_USING = "promoAlreadyUsing";
    
    PromotionFacadeLocal promotionFacade = lookupPromotionFacadeLocal();
    private Promotion promotion;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        promotion = promotionFacade.findByCodePromo(String.valueOf(value));
        if(promotion == null)
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(CODE_PROMO_NOT_EXIT)));
         
        if(promotion.getDrink() == null){
            if(notEnoughDrink(allQuantity())){
                throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(NOT_ENOUGH_DRINK)));
            }
        }
        else{
            Integer quantity = readQuantity(promotion.getDrink());
            if(quantity == null){
                throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(DRINK_NOT_PRESENT)));
            }
            if(notEnoughDrink(quantity))
                throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(NOT_ENOUGH_DRINK) + promotion.getDrink().getText(currentLanguage()).getLabel()));
        } 
        if(PromotionUsed(promotion))
            throw new ValidatorException(
                    new FacesMessage(linkToBundle(context).getString(NOT_ENOUGH_DRINK)));
            
    }
    
    private boolean notEnoughDrink(Integer quantity){
        return promotion.getMinQuantity() != null && quantity < promotion.getMinQuantity();
    }
    
    private Integer readQuantity(Drink drink){
        CaddyMB c = getCaddyMB();
        return (c.getCaddy().get(drink) == null) ? null : c.getCaddy().get(drink).intValue();
    }
    
    private boolean PromotionUsed(Promotion promotion){
        CaddyMB c = getCaddyMB();
        HashMap<Drink,Promotion> promotions  = c.getPromotions();
        if(promotions == null)
            return false;
        if(promotions.containsValue(promotion))
            return true;
        return false;
    }
    
    private Integer allQuantity(){       
         return getCaddyMB().allQuantity();
    }
    
    private CaddyMB getCaddyMB(){
        FacesContext context = FacesContext.getCurrentInstance();
        return  (CaddyMB) context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{caddyMB}", 
                                CaddyMB.class)
                        .getValue(context.getELContext());
    }
    
    private Language currentLanguage(){
        FacesContext context = FacesContext.getCurrentInstance();
        InternationalizationManaged im  = (InternationalizationManaged) context.
                getApplication().getExpressionFactory().
                createValueExpression(context.getELContext(), 
                        "#{internationalizationManaged}", 
                        InternationalizationManaged.class).
                getValue(context.getELContext());
        return im.getCurrentLanguage();
    }
    
    private ResourceBundle linkToBundle(FacesContext context){
        Locale locale = context.getViewRoot().getLocale();
        return ResourceBundle.getBundle(BUNDLE_LOCALE, locale);
    }
    
    private PromotionFacadeLocal lookupPromotionFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PromotionFacadeLocal) c.lookup("java:global/ProjetE-commerce2014v1/ProjetE-commerce2014v1-ejb/PromotionFacade!sessionBeansFacade.PromotionFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
