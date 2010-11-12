/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import static at.ac.tuwien.infosys.aic.Constants.*;
import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.registry.ServiceRegistry;
import at.ac.tuwien.infosys.aic.store.DataStore;
import at.ac.tuwien.infosys.aic.soap.ShippingService;
import at.ac.tuwien.infosys.aic.soap.SupplierService;
import at.ac.tuwien.infosys.aic.soap.WarehouseService;
import java.math.BigDecimal;
import java.util.UUID;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

/**
 *
 * @author tobi, christoph, michael
 */
public class ServiceTest extends BaseIntegrationTest {


    @Test
    public void callWarehouseService() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WarehouseService.class);
        factory.setAddress("http://localhost:8080/Warehouse");
        WarehouseService ss = (WarehouseService) factory.create();

        //order something
        int amount = 5;
        BigDecimal totalPrice;
        Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        totalPrice = ss.order(p, amount);
        assertEquals(new BigDecimal(50), totalPrice);
//        int comp = totalPrice.compareTo(new BigDecimal(50));
//        assertTrue(comp == 0);

        amount = 1;
        p = DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff");
        totalPrice = ss.order(p, amount);
        assertEquals(new BigDecimal(0), totalPrice);
//        comp = totalPrice.compareTo(new BigDecimal(0));
//        assertTrue(comp == 0);

        amount = 6;
        p = DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff");
        assertFalse(ss.check_availability(p, amount).isIsAvailable());

        amount = 5;
        p = DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff");
        assertTrue(ss.check_availability(p, amount).isIsAvailable());

        amount = -1;
        p = DataStore.getInstance().getProduct("aec0737d-e783-4c16-9b26-66040caf4aff");
        try {
            assertFalse(ss.check_availability(p, amount).isIsAvailable());
            fail("exception expected");
        } catch (SOAPFaultException e) {
            assertThat(e.getFault().getFaultString(), is("negative amount fault"));
        }

    }

    @Test
    public void callServiceRegistrySuccessfull() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServiceRegistry.class);
        factory.setAddress(REGISTRYADDRESS);
        ServiceRegistry sr = (ServiceRegistry) factory.create();
        W3CEndpointReference result = sr.getSupplier(DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        assertNotNull(result);
    }

    @Test
    public void callServiceRegistryFail() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ServiceRegistry.class);
        factory.setAddress(REGISTRYADDRESS);
        ServiceRegistry sr = (ServiceRegistry) factory.create();
        Product p = new Product();
        p.setId("gugu");
        p.setName("gaga");
        p.setSingleUnitPrice(BigDecimal.ZERO);
        try {
            W3CEndpointReference result = sr.getSupplier(p);
            fail("exception expected");
        } catch (SOAPFaultException e) {
            assertThat(e.getFault().getFaultString(), is("unknown product fault"));
        }
    }
}
