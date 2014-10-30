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

/**
 *
 * @author Alexandre
 */
@Named
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
    
    public void setToEnglish() {
        locale = new Locale("en");
    }
    
     public void setToFrench() {
        locale = new Locale("fr");
    }
}
