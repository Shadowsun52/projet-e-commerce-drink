/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangType;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LangTypeFacadeLocal {

    void create(LangType langType);

    void edit(LangType langType);

    void remove(LangType langType);

    LangType find(Object id);

    List<LangType> findAll();

    List<LangType> findRange(int[] range);

    int count();
    
}
