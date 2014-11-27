/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangCat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LangCatFacadeLocal {

    void create(LangCat langCat);

    void edit(LangCat langCat);

    void remove(LangCat langCat);

    LangCat find(Object id);

    List<LangCat> findAll();

    List<LangCat> findRange(int[] range);

    int count();
    
}
