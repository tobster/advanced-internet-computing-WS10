/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import static at.ac.tuwien.infosys.aic.Constants.*;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.registry.ServiceRegistry;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
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
public class ServiceRegistryTest extends BaseIntegrationTest {

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
