/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexandre
 */
@Named(value = "internationalizationManaged")
@SessionScoped
public class InternationalizationManaged  implements Serializable{

    /**
     * Creates a new instance of InternationalizationManaged
     */
    
    private Locale locale = new Locale("en");
    
    public InternationalizationManaged() {}
    
    public Locale getLocale() {
        return locale;
    }
    
    public void setLocale(String language) {
        locale = new Locale(language);
        setChosenLanguageCustomerConneted(language);
    }
     
     public String getLanguage() {
         return locale.getLanguage();
     }
     
     private void setChosenLanguageCustomerConneted(String language){
         FacesContext context = FacesContext.getCurrentInstance();
         CustomerMB customerMB = (CustomerMB) 
                context.getApplication().getExpressionFactory()
                        .createValueExpression(context.getELContext(), 
                                "#{customerMB}", 
                                CustomerMB.class)
                        .getValue(context.getELContext());
        if(customerConnected(customerMB))
            customerMB.setChosenLanguage(language);
     }
     
     private boolean customerConnected(CustomerMB customerMB){
        return customerMB != null && customerMB.getConnected();
     }
}
