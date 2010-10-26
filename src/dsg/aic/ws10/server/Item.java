/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsg.aic.ws10.server;

/**
 *
 * @author smolle
 */
public class Item {

    private int quantity = 0;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.quantity;
        return hash;
    }



}

