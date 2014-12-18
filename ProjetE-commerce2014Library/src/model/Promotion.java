/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Alexandre
 */
public class Promotion implements Serializable{
    private static final Character TYPE_AMOUNT = 'A';
    private static final Character TYPE_PERCENTAGE = 'P';
    public static final int TYPE_PROMO_DRINK = 0;
    public static final int TYPE_PROMO_CATEGORY = 1;
    public static final int TYPE_PROMO_GENERAL = 2;
    
    private Integer id;
    private Date dateStart;
    private Date dateEnd;
    private String codePromo;
    private boolean promo_unique;
    private Character typeDiscount;
    private Short percentageDiscount;
    private BigDecimal amountDiscount;
    private Short minQuantity;
    private Category category;
    private Drink drink;
    private HashMap<Language,String> hashMessage;

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Promotion() {
    }

    public Promotion(Integer id, Date dateStart, Date dateEnd, String codePromo,
            boolean promo_unique, Character typeDiscount, Short percentageDiscount,
            BigDecimal amountDiscount, Short minQuantity, Category category, Drink drink) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.codePromo = codePromo;
        this.promo_unique = promo_unique;
        this.typeDiscount = typeDiscount;
        this.percentageDiscount = percentageDiscount;
        this.amountDiscount = amountDiscount;
        this.minQuantity = minQuantity;
        this.category = category;
        this.drink = drink;
        this.hashMessage = new HashMap<>();
    }

    public Promotion(Integer id, Date dateStart, Date dateEnd, String codePromo, 
            boolean promo_unique, Short percentageDiscount, Short minQuantity, 
            Category category, Drink drink) {
        this(id, dateStart, dateEnd, codePromo, promo_unique, TYPE_PERCENTAGE, 
                percentageDiscount, null, minQuantity, category, drink);
    }

    public Promotion(Integer id, Date dateStart, Date dateEnd, String codePromo, 
            boolean promo_unique, BigDecimal amountDiscount, Short minQuantity,
            Category category, Drink drink) {
        this(id, dateStart, dateEnd, codePromo, promo_unique, TYPE_AMOUNT, null, 
                amountDiscount, minQuantity, category, drink);
    }
//</editor-fold>    
    
//<editor-fold defaultstate="collapsed" desc="Type promotion">
    public int typePromotion(){
        if(drink != null)
            return TYPE_PROMO_DRINK;
        
        if(category != null)
            return TYPE_PROMO_CATEGORY;
        
        return TYPE_PROMO_GENERAL;
    }
    
    public boolean isDrinkPromotion(){
        return typePromotion() == Promotion.TYPE_PROMO_DRINK;
    }
    
    public boolean isCategoryPromotion(){
        return typePromotion() == Promotion.TYPE_PROMO_CATEGORY;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="getter & setter">   
    public void addMessage(Language language, String message){
        getHashMessage().put(language, message);
    }
    
    public String getMessage(Language language){
        return getHashMessage().get(language);
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * @return the dateEnd
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * @return the codePromo
     */
    public String getCodePromo() {
        return codePromo;
    }

    /**
     * @param codePromo the codePromo to set
     */
    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    /**
     * @return the promo_unique
     */
    public boolean isPromo_unique() {
        return promo_unique;
    }

    /**
     * @param promo_unique the promo_unique to set
     */
    public void setPromo_unique(boolean promo_unique) {
        this.promo_unique = promo_unique;
    }

    /**
     * @return the typeDiscount
     */
    public Character getTypeDiscount() {
        return typeDiscount;
    }

    /**
     * @param typeDiscount the typeDiscount to set
     */
    public void setTypeDiscount(Character typeDiscount) {
        this.typeDiscount = typeDiscount;
    }

    /**
     * @return the percentageDiscount
     */
    public Short getPercentageDiscount() {
        return percentageDiscount;
    }

    /**
     * @param percentageDiscount the percentageDiscount to set
     */
    public void setPercentageDiscount(Short percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    /**
     * @return the amountDiscount
     */
    public BigDecimal getAmountDiscount() {
        return amountDiscount;
    }

    /**
     * @param amountDiscount the amountDiscount to set
     */
    public void setAmountDiscount(BigDecimal amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    /**
     * @return the minQuantity
     */
    public Short getMinQuantity() {
        return minQuantity;
    }

    /**
     * @param minQuantity the minQuantity to set
     */
    public void setMinQuantity(Short minQuantity) {
        this.minQuantity = minQuantity;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the drink
     */
    public Drink getDrink() {
        return drink;
    }

    /**
     * @param drink the drink to set
     */
    public void setDrink(Drink drink) {
        this.drink = drink;
    }
    
    /**
     * @return the hashMessage
     */
    public HashMap<Language,String> getHashMessage() {
        return hashMessage;
    }

    /**
     * @param hashMessage the hashMessage to set
     */
    public void setHashMessage(HashMap<Language,String> hashMessage) {
        this.hashMessage = hashMessage;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="serialization">
    @Override
    public int hashCode() {        
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        return !((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.Promotion[ id=" + id + " ]";
    }
//</editor-fold>
}
