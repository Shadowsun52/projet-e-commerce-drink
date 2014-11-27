/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangPromotion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LangPromotionFacadeLocal {

    void create(LangPromotion langPromotion);

    void edit(LangPromotion langPromotion);

    void remove(LangPromotion langPromotion);

    LangPromotion find(Object id);

    List<LangPromotion> findAll();

    List<LangPromotion> findRange(int[] range);

    int count();
    
}
