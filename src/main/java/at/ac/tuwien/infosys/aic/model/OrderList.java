/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.model;

import java.util.concurrent.ConcurrentHashMap;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smolle
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) //all Fields ill be serialized
public class OrderList{

    ConcurrentHashMap<String, Order> orders = new ConcurrentHashMap<String, Order>();

    public OrderList() {
    }

    public void add (Order p){
        orders.put(p.getId(), p);
    }


    public Order get (String id){
        return orders.get(id);
    }


}
