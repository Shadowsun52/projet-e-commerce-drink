/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexandre
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByIdcustomer", query = "SELECT c FROM Customer c WHERE c.idcustomer = :idcustomer"),
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name"),
    @NamedQuery(name = "Customer.findByLastname", query = "SELECT c FROM Customer c WHERE c.lastname = :lastname"),
    @NamedQuery(name = "Customer.findByBirthdate", query = "SELECT c FROM Customer c WHERE c.birthdate = :birthdate"),
    @NamedQuery(name = "Customer.findByAddNumstreet", query = "SELECT c FROM Customer c WHERE c.addNumstreet = :addNumstreet"),
    @NamedQuery(name = "Customer.findByAddNamestreet", query = "SELECT c FROM Customer c WHERE c.addNamestreet = :addNamestreet"),
    @NamedQuery(name = "Customer.findByNumphone", query = "SELECT c FROM Customer c WHERE c.numphone = :numphone"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByPassword", query = "SELECT c FROM Customer c WHERE c.password = :password"),
    @NamedQuery(name = "Customer.findByInscriptiondate", query = "SELECT c FROM Customer c WHERE c.inscriptiondate = :inscriptiondate")})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCUSTOMER")
    private Integer idcustomer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ADD_NUMSTREET")
    private String addNumstreet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "ADD_NAMESTREET")
    private String addNamestreet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "NUMPHONE")
    private String numphone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSCRIPTIONDATE")
    @Temporal(TemporalType.DATE)
    private Date inscriptiondate;
    @JoinColumn(name = "CHOSENLANGUAGE", referencedColumnName = "IDLANGUAGE")
    @ManyToOne(optional = false)
    private Language chosenlanguage;
    @JoinColumn(name = "IDLOCALITY", referencedColumnName = "IDLOCALITY")
    @ManyToOne(optional = false)
    private Locality idlocality;

    public Customer() {
    }

    public Customer(Integer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public Customer(Integer idcustomer, String name, String lastname, Date birthdate, String addNumstreet, String addNamestreet, String numphone, String email, String password, Date inscriptiondate) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.addNumstreet = addNumstreet;
        this.addNamestreet = addNamestreet;
        this.numphone = numphone;
        this.email = email;
        this.password = password;
        this.inscriptiondate = inscriptiondate;
    }

    public Integer getIdcustomer() {
        return idcustomer;
    }

    public void setIdcustomer(Integer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddNumstreet() {
        return addNumstreet;
    }

    public void setAddNumstreet(String addNumstreet) {
        this.addNumstreet = addNumstreet;
    }

    public String getAddNamestreet() {
        return addNamestreet;
    }

    public void setAddNamestreet(String addNamestreet) {
        this.addNamestreet = addNamestreet;
    }

    public String getNumphone() {
        return numphone;
    }

    public void setNumphone(String numphone) {
        this.numphone = numphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getInscriptiondate() {
        return inscriptiondate;
    }

    public void setInscriptiondate(Date inscriptiondate) {
        this.inscriptiondate = inscriptiondate;
    }

    public Language getChosenlanguage() {
        return chosenlanguage;
    }

    public void setChosenlanguage(Language chosenlanguage) {
        this.chosenlanguage = chosenlanguage;
    }

    public Locality getIdlocality() {
        return idlocality;
    }

    public void setIdlocality(Locality idlocality) {
        this.idlocality = idlocality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcustomer != null ? idcustomer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.idcustomer == null && other.idcustomer != null) || (this.idcustomer != null && !this.idcustomer.equals(other.idcustomer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Customer[ idcustomer=" + idcustomer + " ]";
    }
    
}
