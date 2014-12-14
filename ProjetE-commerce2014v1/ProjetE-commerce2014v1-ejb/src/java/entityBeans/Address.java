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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "ADDRESS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByIdaddress", query = "SELECT a FROM Address a WHERE a.idaddress = :idaddress"),
    @NamedQuery(name = "Address.findByNamestreet", query = "SELECT a FROM Address a WHERE a.namestreet = :namestreet"),
    @NamedQuery(name = "Address.findByNumstreet", query = "SELECT a FROM Address a WHERE a.numstreet = :numstreet"),
    @NamedQuery(name = "Address.findByPostcode", query = "SELECT a FROM Address a WHERE a.postcode = :postcode"),
    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "Address.findAddress", query = "SELECT a FROM Address a "
            + "WHERE a.idaddress = :idaddress AND a.namestreet = :namestreet "
            + "AND a.numstreet = :numstreet AND a.postcode = :postcode "
            + "AND a.city = :city AND a.idcountry = :idcountry")})
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDADDRESS")
    private Integer idaddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "NAMESTREET")
    private String namestreet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "NUMSTREET")
    private String numstreet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "POSTCODE")
    private String postcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "CITY")
    private String city;
    @JoinColumn(name = "IDCOUNTRY", referencedColumnName = "IDCOUNTRY")
    @ManyToOne(optional = false)
    private Country idcountry;

    public Address() {
    }

    public Address(Integer idaddress) {
        this.idaddress = idaddress;
    }

    public Address(Integer idaddress, String namestreet, String numstreet, String postcode, String city) {
        this.idaddress = idaddress;
        this.namestreet = namestreet;
        this.numstreet = numstreet;
        this.postcode = postcode;
        this.city = city;
    }

    public Integer getIdaddress() {
        return idaddress;
    }

    public void setIdaddress(Integer idaddress) {
        this.idaddress = idaddress;
    }

    public String getNamestreet() {
        return namestreet;
    }

    public void setNamestreet(String namestreet) {
        this.namestreet = namestreet;
    }

    public String getNumstreet() {
        return numstreet;
    }

    public void setNumstreet(String numstreet) {
        this.numstreet = numstreet;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getIdcountry() {
        return idcountry;
    }

    public void setIdcountry(Country idcountry) {
        this.idcountry = idcountry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaddress != null ? idaddress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.idaddress == null && other.idaddress != null) || (this.idaddress != null && !this.idaddress.equals(other.idaddress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Address[ idaddress=" + idaddress + " ]";
    }
    
}
