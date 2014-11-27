/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexandre
 */
@Stateless
public class AddressFacade extends AbstractFacade<Address> implements AddressFacadeLocal {
    @EJB
    private CountryFacadeLocal countryFacade;
    
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }

    @Override
    public model.Address converterToModel(Address entity) {
        return new model.Address(entity.getIdaddress(), 
                entity.getNamestreet(), entity.getNumstreet(),
                entity.getPostcode(), entity.getCity(), 
                new CountryFacade().converterToModel(entity.getIdcountry()));
    }

    @Override
    public Address converterToEntity(model.Address address) {
        Address entity = new Address(address.getId(), address.getNameStreet(), 
                address.getNumStreet(), address.getPostCode(), 
                address.getCity());
        entity.setIdcountry(countryFacade.find(address.getCountry().getId()));
        return entity;
    }

    @Override
    public void create(model.Address address) {
        create(converterToEntity(address));
    }

    @Override
    public void edit(model.Address address) {
        edit(converterToEntity(address));
    }

    @Override
    public model.Address findAddress(Object id) {
        return converterToModel(find(id));
    }

    @Override
    public ArrayList<model.Address> findAllAddress() {
        ArrayList<model.Address> listAddress = new ArrayList();
        findAll().stream().forEach((address) -> {
            listAddress.add(converterToModel(address));
        });
        return listAddress;
    }
    
}
