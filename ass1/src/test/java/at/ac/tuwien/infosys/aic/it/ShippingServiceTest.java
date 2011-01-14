/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.soap.ShippingService;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownAddressFault;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class ShippingServiceTest extends BaseIntegrationTest{

    private DataStore ds = DataStore.getInstance();
    private HttpClient httpclient = new DefaultHttpClient();

    @Before
    public void init() {
        ds.init();
    }

    @Test
    public void callShippingService() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/Shipping");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
        items[0].setProduct(DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        items[1] = new Item();
        items[1].setProduct(DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff"));

        Address address = new Address();
        address.setId("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb");
        String result;
        try {
            result = ss.ship_items(items, address);
            UUID.fromString(result);
        } catch (UnknownProductFault ex) {
            Logger.getLogger(ShippingServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownAddressFault ex) {
            Logger.getLogger(ShippingServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }     

    }
    
    //address doesnt exist
    @Test
    public void callShippingServiceWrongAddress() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/Shipping");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
        items[0].setProduct(DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        items[1] = new Item();
        items[1].setProduct(DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff"));

        Address address = new Address();
        address.setId("WrongAddress");
        
        try {
                ss.ship_items(items, address);

            }catch (Exception e) {
            assertThat(e.getMessage(), is("unknown address fault"));
        }
        
    }

    //address doesnt exist
    @Test
    public void callShippingServiceNullAddress() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/Shipping");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
        items[0].setProduct(DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        items[1] = new Item();
        items[1].setProduct(DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff"));

        Address address = null;

        try {

        ss.ship_items(items, address);

        } catch (Exception e) {
            assertThat(e.getMessage(), is("unknown address fault"));
        }

    }

    //product doesnt exist
    @Test
    public void callShippingServiceWrongProduct() {
                JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ShippingService.class);
        factory.setAddress("http://localhost:8080/Shipping");
        ShippingService ss = (ShippingService) factory.create();

        Item[] items = new Item[2];
        items[0] = new Item();
        Product p = new Product();
        p.setId("noID");
        items[0].setProduct(p);


        Address address = new Address();
        address.setId("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb");

        try {
            try {
                ss.ship_items(items, address);
            } catch (UnknownProductFault ex) {
                Logger.getLogger(ShippingServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownAddressFault ex) {
                Logger.getLogger(ShippingServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SOAPFaultException e) {
            assertThat(e.getMessage(), is("unknown product fault"));
        }

    }


}
