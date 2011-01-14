package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.soap.SupplierService;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
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
import static at.ac.tuwien.infosys.aic.Constants.*;

public class SupplierServiceTest extends BaseIntegrationTest {

    private DataStore ds = DataStore.getInstance();
    private HttpClient httpclient = new DefaultHttpClient();

    @Before
    public void init() {
        ds.init();
    }

    @Test
    public void callSupplierService1() {
        try {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(SupplierService.class);
            factory.setAddress(SUPPLIER1ADDRESS);
            SupplierService ss = (SupplierService) factory.create();
            //order something
            int ammount = 5;
            BigDecimal totalPrice;
            //price of product is 0
            Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
            totalPrice = ss.order(p, ammount);
            int comp = totalPrice.compareTo(new BigDecimal(50));
            assertTrue(comp == 0);
        } catch (UnknownProductFault ex) {
            Logger.getLogger(SupplierServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void callSupplierService1UnknownProductTest() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SupplierService.class);
        factory.setAddress(SUPPLIER1ADDRESS);
        SupplierService ss = (SupplierService) factory.create();

        //order something
        int ammount = 5;
        BigDecimal totalPrice;
        //price of product is 0
        Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        p.setId("unknownProduct");

        try {
            totalPrice = ss.order(p, ammount);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("unknown product fault"));
        }

    }

    @Test
    public void callSupplierServce2() {
        try {
            JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
            factory.setServiceClass(SupplierService.class);
            factory.setAddress(SUPPLIER2ADDRESS);
            SupplierService ss = (SupplierService) factory.create();
            //order something
            int ammount = 5;
            BigDecimal totalPrice;
            //price of product is 0
            Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
            totalPrice = ss.order(p, ammount);
            int comp = totalPrice.compareTo(new BigDecimal(50));
            assertTrue(comp == 0);
        } catch (UnknownProductFault ex) {
            Logger.getLogger(SupplierServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void callSupplierService2UnknownProductTest() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(SupplierService.class);
        factory.setAddress(SUPPLIER2ADDRESS);
        SupplierService ss = (SupplierService) factory.create();

        //order something
        int ammount = 5;
        BigDecimal totalPrice;
        //price of product is 0
        Product p = DataStore.getInstance().getProduct("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        p.setId("unknownProduct");

        try {
            totalPrice = ss.order(p, ammount);
        } catch (Exception e) {
            assertThat(e.getMessage(), is("unknown product fault"));
        }

    }
}
