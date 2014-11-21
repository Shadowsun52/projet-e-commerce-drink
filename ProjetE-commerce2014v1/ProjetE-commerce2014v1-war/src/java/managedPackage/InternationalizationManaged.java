/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import model.Language;
import sessionBeansFacade.LanguageFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "internationalizationManaged")
@SessionScoped
public class InternationalizationManaged  implements Serializable{
    @EJB
    private LanguageFacadeLocal languageFacade;

    /**
     * Creates a new instance of InternationalizationManaged
     */
    
    private Locale locale;
    private ArrayList<Language> languages;
    private Language currentLanguage;
    
    public InternationalizationManaged() {
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    @PostConstruct
    public void init(){
        setLanguages(languageFacade.findAllLanguages());
        setCurrentLanguage(languages.get(0));
        locale = new Locale(getCurrentLanguage().getShortLabel());
    }
    
    public void setLocale(Language language) {
        locale = new Locale(language.getShortLabel());
//        setChosenLanguageCustomerConneted(language);
    }

     private void setChosenLanguageCustomerConneted(String language){
//         FacesContext context = FacesContext.getCurrentInstance();
//         CustomerMB customerMB = (CustomerMB) 
//                context.getApplication().getExpressionFactory()
//                        .createValueExpression(context.getELContext(), 
//                                "#{customerMB}", 
//                                CustomerMB.class)
//                        .getValue(context.getELContext());
//        if(customerConnected(customerMB))
//            customerMB.setChosenLanguage(language);
     }
     
     private boolean customerConnected(CustomerMB customerMB){
        return customerMB != null && customerMB.getConnected();
     }

    /**
     * @return the languages
     */
    public ArrayList<Language> getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(ArrayList<Language> languages) {
        this.languages = languages;
    }

    /**
     * @return the currentLanguage
     */
    public Language getCurrentLanguage() {
        return currentLanguage;
    }

    /**
     * @param currentLanguage the currentLanguage to set
     */
    public void setCurrentLanguage(Language currentLanguage) {
        this.currentLanguage = currentLanguage;
    }
}
