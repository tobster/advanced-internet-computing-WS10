/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.soap.ShippingService;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

/**
 *
 * @author tobi
 */
public class ShippingServiceIT {

    @Test
    public void callServicesuccess() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//        factory.getInInterceptors().add(new LoggingInInterceptor());
//        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/soap/ShippingPT");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
        Product product = new Product();
        product.setId("aec0737d-e783-4c16-9b26-66040caf4aff");
        items[0].setProduct(product);
        items[1] = new Item();
        product = new Product();
        product.setId("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        items[1].setProduct(product);

        Address address = new Address();
        address.setId("a888070b-96f3-47ac-9fe9-dfe2dadc00cb");
        address.setCity("bla");
        address.setZipCode("1234");
        address.setStreet("bul");
        address.setHouse(1);
        address.setDoor(2);
        String result = ss.ship_items(items, address);
        UUID.fromString(result);
    }
}
