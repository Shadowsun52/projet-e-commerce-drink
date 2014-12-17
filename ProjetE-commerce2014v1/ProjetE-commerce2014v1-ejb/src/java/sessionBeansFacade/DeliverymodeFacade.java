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
    
    @Override
    public Deliverymode converterToEntity(DeliveryMode delMode){
        Deliverymode entity = new Deliverymode(delMode.getId(), 
                new BigDecimal(delMode.getCurrentpostalcharges()));
        entity.setLangDelmodeCollection(getAllInfo(delMode.getHashLabel(),
                delMode.getId()));
        return entity;
    }
    
    private Collection<LangDelmode> getAllInfo(
            HashMap<Language,InfoDelMode> hashLabel,Integer id) {
        Collection<LangDelmode> labels = new ArrayList();
        for (Map.Entry<Language, InfoDelMode> info : hashLabel.entrySet()) {
            LangDelmode langDelMode = new LangDelmode(new LangDelmodePK(id, 
                    info.getKey().getId()), info.getValue().getLabel(), 
                    info.getValue().getDescription());
            langDelMode.setLanguage(languageFacade.converterToEntity(info.getKey()));
            labels.add(langDelMode);
        }
        return labels;
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
