/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.rest.CustomerManagementService;
import at.ac.tuwien.infosys.aic.store.DataStore;
import static at.ac.tuwien.infosys.aic.Constants.*;
import at.ac.tuwien.infosys.aic.model.Customer;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author smolle
 */
public class CustomerRestServiceTest extends BaseIntegrationTest {

    private CustomerManagementService service = JAXRSClientFactory.create(CUSTOMERMANAGEMENT,CustomerManagementService.class);
    private DataStore ds = DataStore.getInstance();


    @Test
    public void testGetExsistingCustomer() {
       Customer c =  service.getCustomer(CUSTOMER1);
       assertNotNull(c);
       assertThat(c, is(ds.getCustomer(CUSTOMER1)));


    }
}
