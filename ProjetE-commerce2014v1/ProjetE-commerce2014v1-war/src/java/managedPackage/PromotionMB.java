/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import model.Promotion;
import sessionBeansFacade.PromotionFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "promotionMB")
@SessionScoped
public class PromotionMB implements Serializable {
    @EJB
    private PromotionFacadeLocal promotionFacade;
    
    private ArrayList<Promotion> promotions;
    
    
    /**
     * Creates a new instance of PromotionMB
     */
    public PromotionMB() {
    }

    @PostConstruct
    public void init(){
        promotions = promotionFacade.findAllPromotions();
    } 
    
//<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    /**
     * @return the promotions
     */
    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }
//</editor-fold>
}
