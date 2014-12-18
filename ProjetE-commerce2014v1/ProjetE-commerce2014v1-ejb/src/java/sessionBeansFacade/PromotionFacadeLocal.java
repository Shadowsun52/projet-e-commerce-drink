/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Promotion;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface PromotionFacadeLocal {

    void create(Promotion promotion);

    void edit(Promotion promotion);

    void remove(Promotion promotion);

    Promotion find(Object id);

    List<Promotion> findAll();

    List<Promotion> findRange(int[] range);

    int count();
    
    public model.Promotion converterToModel(Promotion entity);
    
    public Promotion converterToEntity(model.Promotion promotion);
    
    public ArrayList<model.Promotion> findAllPromotions();
    
    public model.Promotion findPromotion(Object id);
}
