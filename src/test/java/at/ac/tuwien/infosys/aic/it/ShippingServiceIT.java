/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.server.Server;
import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.store.DataStore;
import at.ac.tuwien.infosys.aic.soap.ShippingService;
import java.util.UUID;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.junit.Test;

/**
 *
 * @author tobi, christoph
 */
public class ShippingServiceIT {

    private static Server server;

    @BeforeClass
    public static void intitServer() {
        server = new Server();
    }

    @Test
    public  void callServicesuccess() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//        factory.getInInterceptors().add(new LoggingInInterceptor());
//        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/Shipping");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
//        Product product = new Product();
//        product.setId("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        items[0].setProduct(DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        items[1] = new Item();
        //product = new Product();
        //product.setId("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        items[1].setProduct(DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff"));
        ;

        Address address = new Address();
        address.setId("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb");
//        address.setCity("Linz");
//        address.setZipCode("1234");
//        address.setStreet("bul");
//        address.setHouse(1);
//        address.setDoor(2);
        String result = ss.ship_items(items, address);
        UUID.fromString(result);

    }

    @AfterClass
    public static void stop() {
        server.stop();
        server= null;
    }
}
