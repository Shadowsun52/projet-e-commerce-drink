/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Locality;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface LocalityFacadeLocal {

    void create(Locality locality);

    void edit(Locality locality);

    void remove(Locality locality);

    Locality find(Object id);

    List<Locality> findAll();

    List<Locality> findRange(int[] range);

    int count();
    
    public List<Locality> findByCountry(Integer idCountry);
}
