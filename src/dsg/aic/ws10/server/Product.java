/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsg.aic.ws10.server;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author smolle
 */
public class Product {

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
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.singleUnitPrice != other.singleUnitPrice && (this.singleUnitPrice == null || !this.singleUnitPrice.equals(other.singleUnitPrice))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 61 * hash + (this.singleUnitPrice != null ? this.singleUnitPrice.hashCode() : 0);
        return hash;
    }

    

}
