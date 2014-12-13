/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedPackage;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import model.DeliveryMode;
import sessionBeansFacade.DeliverymodeFacadeLocal;

/**
 *
 * @author Alexandre
 */
@Named(value = "deliveryModeMB")
@ApplicationScoped
public class DeliveryModeMB {
    @EJB
    private DeliverymodeFacadeLocal deliverymodeFacade;
    
    private ArrayList<DeliveryMode> listDeliveryModes;
    
    /**
     * Creates a new instance of DeliveryModeMB
     */
    public DeliveryModeMB() {
        
    }
    
    @PostConstruct
    public void init(){
        listDeliveryModes = deliverymodeFacade.findAllDeliveryMode();
    }

    /**
     * @return the listDeliveryModes
     */
    public ArrayList<DeliveryMode> getListDeliveryModes() {
        return listDeliveryModes;
    }

    /**
     * @param listDeliveryModes the listDeliveryModes to set
     */
    public void setListDeliveryModes(ArrayList<DeliveryMode> listDeliveryModes) {
        this.listDeliveryModes = listDeliveryModes;
    }
    
    
}
