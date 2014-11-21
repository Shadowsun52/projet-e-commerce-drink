/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "PARAMETER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parameter.findAll", query = "SELECT p FROM Parameter p"),
    @NamedQuery(name = "Parameter.findByIdparameter", query = "SELECT p FROM Parameter p WHERE p.idparameter = :idparameter"),
    @NamedQuery(name = "Parameter.findBySitename", query = "SELECT p FROM Parameter p WHERE p.sitename = :sitename")})
public class Parameter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPARAMETER")
    private Integer idparameter;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "SITENAME")
    private String sitename;
    @JoinColumn(name = "IDADDRESS", referencedColumnName = "IDADDRESS")
    @ManyToOne(optional = false)
    private Address idaddress;
    @JoinColumn(name = "DEFAULTLANGUAGE", referencedColumnName = "IDLANGUAGE")
    @ManyToOne(optional = false)
    private Language defaultlanguage;

    public Parameter() {
    }

    public Parameter(Integer idparameter) {
        this.idparameter = idparameter;
    }

    public Parameter(Integer idparameter, String sitename) {
        this.idparameter = idparameter;
        this.sitename = sitename;
    }

    public Integer getIdparameter() {
        return idparameter;
    }

    public void setIdparameter(Integer idparameter) {
        this.idparameter = idparameter;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public Address getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Address idaddress) {
        this.idaddress = idaddress;
    }

    public Language getDefaultlanguage() {
        return defaultlanguage;
    }

    public void setDefaultlanguage(Language defaultlanguage) {
        this.defaultlanguage = defaultlanguage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparameter != null ? idparameter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter) object;
        if ((this.idparameter == null && other.idparameter != null) || (this.idparameter != null && !this.idparameter.equals(other.idparameter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Parameter[ idparameter=" + idparameter + " ]";
    }
    
}
