/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Language;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alexandre
 */
@Local
public interface LanguageFacadeLocal {

    void create(Language language);

    void edit(Language language);

    void remove(Language language);

    Language find(Object id);

    List<Language> findAll();

    List<Language> findRange(int[] range);

    int count();
    
    public model.Language findLanguage(Object id);
    
    public ArrayList<model.Language> findAllLanguages();
    
    public model.Language findByShortLabel(String shortlabel);
    
    public model.Language converterToModel(Language entity);
    
    public Language converterToEntity(model.Language language);
}
