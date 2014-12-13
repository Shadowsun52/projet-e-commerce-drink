/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Deliverymode;
import entityBeans.LangDelmode;
import entityBeans.LangDelmodePK;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.DeliveryMode;
import model.InfoDelMode;
import model.Language;

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

//<editor-fold defaultstate="collapsed" desc="Converter">
    @Override
    public model.DeliveryMode converterToModel(Deliverymode entity) {
        model.DeliveryMode deliveryMode = new DeliveryMode(
                entity.getIddeliverymode(), 
                entity.getCurrentpostalcharges().doubleValue());
        for(LangDelmode langDM : entity.getLangDelmodeCollection()){
            deliveryMode.addInfoDelMode(languageFacade.
                    converterToModel(langDM.getLanguage()), 
                    new InfoDelMode(langDM.getLabel(), langDM.getDescription()));
        }
        return deliveryMode;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="CRUD">   
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
//</editor-fold>
}
