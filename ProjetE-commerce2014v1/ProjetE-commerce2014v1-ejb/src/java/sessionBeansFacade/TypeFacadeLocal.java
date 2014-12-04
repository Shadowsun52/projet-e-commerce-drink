/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Type;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface TypeFacadeLocal {

    void create(Type type);

    void edit(Type type);

    void remove(Type type);

    Type find(Object id);

    List<Type> findAll();

    List<Type> findRange(int[] range);

    int count();
    
    ArrayList<model.Type> findByCateg(int idCateg);
    
    ArrayList<entityBeans.Type> findEntityByCateg(int idCateg);
    
    model.Type converterToModel(Type entity);
    
    model.Type findType(Object id);
}
