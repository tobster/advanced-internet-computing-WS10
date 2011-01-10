/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.model;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smolle
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) //all Fields ill be serialized
public class Product {

    @XmlAttribute
    private String id;
    
    private String name;

    private BigDecimal singleUnitPrice = new BigDecimal(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSingleUnitPrice() {
        return singleUnitPrice;
    }

    public void setSingleUnitPrice(BigDecimal singleUnitPrice) {
        this.singleUnitPrice = singleUnitPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }    

}
