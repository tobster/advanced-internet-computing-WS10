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
public class CustomerList{

    ConcurrentHashMap<String, Customer> customers = new ConcurrentHashMap<String, Customer>();

    public CustomerList() {
    }

    public void add (Customer c){
        this.customers.put(c.getId(), c);
    }


    public Customer get (String id){
        return customers.get(id);
    }

}
