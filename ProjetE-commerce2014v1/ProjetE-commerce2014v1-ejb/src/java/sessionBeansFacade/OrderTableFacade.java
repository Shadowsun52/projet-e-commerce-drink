/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeansFacade;

import entityBeans.Address;
import entityBeans.LineOrder;
import entityBeans.LineOrderPK;
import entityBeans.OrderTable;
import entityBeans.Promotion;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Customer;
import model.EmailSender;
import model.Order;

/**
 *
 * @author Alexandre
 */
@Stateless
public class OrderTableFacade extends AbstractFacade<OrderTable> implements OrderTableFacadeLocal {  
    private static final String PATCH_SITE = "http://localhost:8080/" 
            +"ProjetE-commerce2014v1-war/faces/yourAccountxhtml";
    private static final String INTRO_SUBJECT = "EmailIntroSubject";
    private static final String ORDER_SUBJECT="orderSubject";
    private static final String ORDER_SUBJECT2="orderSubject2";
    private static final String ORDER_BODY1="orderBodyMail1";
    private static final String ORDER_BODY2="orderBodyMail2";
    private static final String ORDER_BODY3="orderBodyMail3";
    
    @EJB
    private PromotionFacadeLocal promotionFacade;
    @EJB
    private DeliverymodeFacadeLocal deliverymodeFacade;
    @EJB
    private LineOrderFacadeLocal lineOrderFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    @EJB
    private AddressFacadeLocal addressFacade;
    
    @PersistenceContext(unitName = "ProjetE-commerce2014v1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderTableFacade() {
        super(OrderTable.class);
    }

//<editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public boolean OrderUsingAddress(Address address) {
        try {
            Query query = em.createNamedQuery("OrderTable.findByDelAddress");
            query.setParameter("delAddress", address);
            query.getSingleResult();
            return true;
        }
        catch(NoResultException e){
            return false;
        }
    }
    
    @Override
    public ArrayList<Order> findAllOrders() {
        ArrayList<Order> listOrders = new ArrayList();
        findAll().stream().forEach((order) ->{
            listOrders.add(converterToModel(order));
        });
        return listOrders;
    }

    @Override
    public Order findOrder(Object numOrder) {
        return converterToModel(find(numOrder));
    }

    @Override
    public ArrayList<Order> findByCustomer(Integer customerId)throws Exception{
        try{
            Query query = em.createNamedQuery("OrderTable.findByCustomer");
            query.setParameter("idcustomer", customerId);
            ArrayList<Order> listOrders = new ArrayList();
            query.getResultList().stream().forEach((order) ->{
                listOrders.add(converterToModel((OrderTable)order));
            });
            return listOrders;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    @Override
    public void create(Order order, ResourceBundle bundle) throws Exception {
        OrderTable entity = converterToEntity(order);
        create(entity);
        em.flush();
        entity.setLineOrderCollection(attachLineOrder(order, entity));
        createLineOrder(entity);
        EmailSender.sendEmail(order.getCustomer().getEmail(), 
                createSubjectForEmailSaveOrder(bundle, entity.getNumorder()), 
                createBodyForEmailSaveOrder(bundle, order.getCustomer(),
                        entity.getNumorder()));
        
    }
    
    private void createLineOrder(OrderTable entity){
        entity.getLineOrderCollection().stream().forEach((lineOrder) ->{
            lineOrder.setLineOrderPK(
                    new LineOrderPK(lineOrder.getDrink().getIddrink(), 
                            entity.getNumorder()));
            lineOrderFacade.create(lineOrder);
        });
    }
    
    private String createSubjectForEmailSaveOrder(ResourceBundle bundle, 
            Integer numOrder){
        return bundle.getString(INTRO_SUBJECT) + 
                bundle.getString(ORDER_SUBJECT) + numOrder 
                + bundle.getString(ORDER_SUBJECT2);
    }
    
    private String createBodyForEmailSaveOrder(ResourceBundle bundle, 
            Customer customer, Integer numOrder){
        return "<h1>" + createSubjectForEmailSaveOrder(bundle, numOrder) 
                + "</h1><p>" + customer.getName() + " " 
                + customer.getLastname() + ",<p></p>"
                + bundle.getString(ORDER_BODY1) + numOrder 
                + bundle.getString(ORDER_BODY2) + "</p><p>" 
                + bundle.getString(ORDER_BODY3) + "<a href=\"" + PATCH_SITE
                + "\">BeerForTheWin</a></p>";
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="converter">
    @Override
    public Order converterToModel(OrderTable entity) {
        Order order = new Order(entity.getNumorder(), entity.getValidationdate(), 
                entity.getPaymentdate(), entity.getPostalcharges().doubleValue(), 
                deliverymodeFacade.converterToModel(entity.getIddeliverymode()), 
                addressFacade.converterToModel(entity.getDelAddress()), 
                customerFacade.converterToModel(entity.getIdcustomer()));
        for (LineOrder lineOrder : entity.getLineOrderCollection()) {
            order.getLines().add(lineOrderFacade.converterToModel(lineOrder)); 
        }
        for (Promotion promotion : entity.getPromotionCollection()) {
            order.getPromotions().add(promotionFacade.converterToModel(promotion));
        }
        return order;
    }
    
    @Override
    public OrderTable converterToEntity(Order order) {
        OrderTable entity = new OrderTable(order.getNumOrder(), 
                order.getValidationDate(), new BigDecimal(order.getPostalcharges()).setScale(5,RoundingMode.HALF_UP));
        entity.setDelAddress(addressFacade.converterToEntity(order.getDelAddress()));
        entity.setIdcustomer(customerFacade.converterToEntity(order.getCustomer()));
        entity.setIddeliverymode(deliverymodeFacade.converterToEntity(
                order.getDeliveryMode()));
        entity.setPaymentdate(order.getPaymentdate());
        entity.setPromotionCollection(attachPromotion(order, entity));
        return entity;
    }

    private ArrayList<LineOrder> attachLineOrder(Order order, OrderTable entity){
        ArrayList<LineOrder> linesOrder = new ArrayList<>();
        order.getLines().stream().forEach((line) ->{
            linesOrder.add(lineOrderFacade.converterToEntity(line, entity));
        });
        return linesOrder;
    }
    
    private ArrayList<Promotion> attachPromotion(Order order, OrderTable entity){
        ArrayList<Promotion> promotions = new ArrayList<>();
        order.getPromotions().stream().forEach((promotion) ->{
            promotions.add(promotionFacade.converterToEntity(promotion));
        });
        return promotions;
    }
//</editor-fold>
}
