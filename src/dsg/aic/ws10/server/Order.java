/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsg.aic.ws10.server;

import java.util.Date;

/**
 *
 * @author smolle
 */
public class Order {

    private String id;
    private Date orderDate;

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.orderDate != null ? this.orderDate.hashCode() : 0);
        return hash;
    }




}
