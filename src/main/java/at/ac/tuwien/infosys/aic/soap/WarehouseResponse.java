/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class WarehouseResponse {

    boolean isAvailable;
    int deliveryTime;

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }   

}
