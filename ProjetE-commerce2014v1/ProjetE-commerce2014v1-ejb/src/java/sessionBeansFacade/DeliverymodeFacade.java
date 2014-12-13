/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Deliverymode;
import entityBeans.LangDelmode;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.DeliveryMode;

/**
 *
 * @author Alexandre
 */
@Stateless
public class DeliverymodeFacade extends AbstractFacade<Deliverymode> implements DeliverymodeFacadeLocal {
    @EJB
    private LanguageFacadeLocal languageFacade;
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeliverymodeFacade() {
        super(Deliverymode.class);
    }

    @Override
    public model.DeliveryMode converterToModel(Deliverymode entity) {
        model.DeliveryMode deliveryMode = new DeliveryMode(
                entity.getIddeliverymode(), 
                entity.getCurrentpostalcharges().doubleValue());
        for(LangDelmode langDM : entity.getLangDelmodeCollection()){
            deliveryMode.addLabel(languageFacade.
                    converterToModel(langDM.getLanguage()), langDM.getLabel());
        }
        return deliveryMode;
    }

    @Override
    public model.DeliveryMode findDeliveryMode(Object id) {
        return converterToModel(find(id));
    }

    @Override
    public ArrayList<model.DeliveryMode> findAllDeliveryMode() {
        ArrayList<model.DeliveryMode> listDeliveryModes = new ArrayList();
        findAll().stream().forEach((deliveryMode)-> {
            listDeliveryModes.add(converterToModel(deliveryMode));
        });
        return listDeliveryModes;
    }
    
}
