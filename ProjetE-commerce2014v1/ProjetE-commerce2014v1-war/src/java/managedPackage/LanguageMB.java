/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import entityBeans.Language;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import sessionBeansFacade.LanguageFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "languageMB")
@ApplicationScoped
public class LanguageMB {
    @EJB
    private LanguageFacadeLocal languageFacade;

    /**
     * Creates a new instance of LanguageMB
     */
    public LanguageMB() {
    }
    
    public ArrayList<Language> getAllLanguage() {
        return new ArrayList(languageFacade.findAll());
    }
    
    public String getSlogan(String shortLanguage) {
        return languageFacade.findByShortLabel(shortLanguage).getSlogan();
    }
}
