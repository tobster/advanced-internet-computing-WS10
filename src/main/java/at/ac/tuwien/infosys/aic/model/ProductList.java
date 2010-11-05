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
public class ProductList{

       ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<String, Product>();

    public ProductList() {
    }

    public void add (Product p){
        products.put(p.getId(), p);
    }


    public Product get (String id){
        return products.get(id);
    }

}
