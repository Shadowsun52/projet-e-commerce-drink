/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Alexandre
 */
public class DeliveryMode implements Serializable{
    private Integer id;
    private Double currentpostalcharges;
    private HashMap<Language,InfoDelMode> hashLabel;

    public DeliveryMode() {
    }

    public DeliveryMode(int id, double currentpostalcharges) {
        this.id = id;
        this.currentpostalcharges = currentpostalcharges;
        this.hashLabel = new HashMap();
    }

// <editor-fold defaultstate="collapsed" desc="getter & setter"> 
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
     * @return the currentpostalcharges
     */
    public Double getCurrentpostalcharges() {
        return currentpostalcharges;
    }

    /**
     * @param currentpostalcharges the currentpostalcharges to set
     */
    public void setCurrentpostalcharges(Double currentpostalcharges) {
        this.currentpostalcharges = currentpostalcharges;
    }

    /**
     * @return the hashLabel
     */
    public HashMap<Language,InfoDelMode> getHashLabel() {
        return hashLabel;
    }

    /**
     * @param hashLabel the hashLabel to set
     */
    public void setHashLabel(HashMap<Language,InfoDelMode> hashLabel) {
        this.hashLabel = hashLabel;
    }
    
    public void addInfoDelMode(Language language, InfoDelMode infoDelMode){
        this.hashLabel.put(language, infoDelMode);
    }
    
    public InfoDelMode getInfoDelMode(Language language) {
        return hashLabel.get(language);
    }
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="serialization">
    @Override
    public int hashCode() {        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryMode)) {
            return false;
        }
        DeliveryMode other = (DeliveryMode) object;
        return !((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "model.DeliveryMode[ id=" + id + " ]";
    }
// </editor-fold>
}
