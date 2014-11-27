/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Alex
 */
@Named(value = "typeMB")
@ViewScoped
public class TypeMB implements Serializable{

    /**
     * Creates a new instance of TypeMB
     */
    public TypeMB() {
    }
    
}
