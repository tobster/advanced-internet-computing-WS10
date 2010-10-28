/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsg.aic.ws10.data;

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
public class AddressList  {

    ConcurrentHashMap<String, Addresses> addresses = new ConcurrentHashMap<String, Addresses>();

    public AddressList() {
    }

    public void add (Addresses a){
        addresses.put(a.getId(), a);
    }


    public Addresses get (String id){
        return addresses.get(id);
    }

}


