/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.LangDelmode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface LangDelmodeFacadeLocal {

    void create(LangDelmode langDelmode);

    void edit(LangDelmode langDelmode);

    void remove(LangDelmode langDelmode);

    LangDelmode find(Object id);

    List<LangDelmode> findAll();

    List<LangDelmode> findRange(int[] range);

    int count();
    
}
