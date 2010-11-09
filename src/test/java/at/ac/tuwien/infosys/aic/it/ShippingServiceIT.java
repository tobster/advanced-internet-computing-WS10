/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.server.Server;
import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import at.ac.tuwien.infosys.aic.soap.ShippingService;
import at.ac.tuwien.infosys.aic.soap.SupplierImpl;
import at.ac.tuwien.infosys.aic.soap.SupplierService;
import at.ac.tuwien.infosys.aic.soap.WarehouseService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author tobi, christoph, michael
 */
public class ShippingServiceIT {

    private static Server server;

    @BeforeClass
    public static void intitServer() {
        server = new Server();
    }

    @Test
    public  void callShippingService() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/Shipping");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
        items[0].setProduct(DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        items[1] = new Item();
        items[1].setProduct(DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff"));
        ;

        Address address = new Address();
        address.setId("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb");
        String result = ss.ship_items(items, address);
        UUID.fromString(result);

    }
        @Test
        public  void callSupplierService1() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SupplierService.class);
        factory.setAddress("http://localhost:8080/Supplier1");
        SupplierService ss = (SupplierService) factory.create();

        //order something
        int ammount = 5;
        BigDecimal totalPrice;
        //price of product is 0
        Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        totalPrice = ss.order(p, ammount);
        int comp = totalPrice.compareTo(new BigDecimal(50));
        assertTrue(comp == 0);
    }

        @Test
        public  void callSupplierServce2() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SupplierService.class);
        factory.setAddress("http://localhost:8080/Supplier1");
        SupplierService ss = (SupplierService) factory.create();

        //order something
        int ammount = 5;
        BigDecimal totalPrice;
        //price of product is 0
        Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        totalPrice = ss.order(p, ammount);
        int comp = totalPrice.compareTo(new BigDecimal(50));
        assertTrue(comp == 0);
    }

    @Test
    public  void callWarehouseService() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WarehouseService.class);
        factory.setAddress("http://localhost:8080/Warehouse");
        WarehouseService ss = (WarehouseService) factory.create();

        //order something
        int ammount = 5;
        BigDecimal totalPrice;
        Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        totalPrice = ss.order(p, ammount);
        int comp = totalPrice.compareTo(new BigDecimal(50));
        assertTrue(comp == 0);

        ammount = 1;
        p = DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff");
        totalPrice = ss.order(p, ammount);
        comp = totalPrice.compareTo(new BigDecimal(0));
        assertTrue(comp == 0);

    }


    @AfterClass
    public static void stop() {
        if (server != null) {
        server.stop();
        server= null;
        }
    }
}
