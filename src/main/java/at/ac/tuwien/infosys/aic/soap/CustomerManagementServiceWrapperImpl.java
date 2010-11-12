/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownCustomerFault;
import javax.ws.rs.WebApplicationException;
import at.ac.tuwien.infosys.aic.rest.CustomerManagementService;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import at.ac.tuwien.infosys.aic.model.Customer;
import javax.xml.namespace.QName;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.jaxrs.client.WebClient;
import static at.ac.tuwien.infosys.aic.Constants.*;

//@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.soap.CustomManagerServiceWrapper")
public class CustomerManagementServiceWrapperImpl implements CustomerManagementServiceWrapper {
    private Logger log = Logger.getLogger("CustomerManagementServiceWrapperImpl");
    private CustomerManagementService customerManagementService;
    public CustomerManagementServiceWrapperImpl() {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").build();
        customerManagementService = JAXRSClientFactory.create( uri.toString() , CustomerManagementService.class);
        WebClient.client(customerManagementService).accept("application/json");
    }


    @Override
    public Customer get(String id) {

        try {
           return customerManagementService.getCustomer(id);
        } catch (WebApplicationException wea) {

            throw new UnknownCustomerFault();
        }      
        
    }

//BookStore store = JAXRSClientFactory.create("http://bookstore.com", BookStore.class);
// (1) remote GET call to http://bookstore.com/bookstore
//Books books = store.getAllBooks();
// (2) no remote call
//BookResource subresource = store.getBookSubresource(1);
// {3} remote GET call to http://bookstore.com/bookstore/1
//Book b = subresource.getDescription();



    @Override
    public void put(Customer customer) {
        try{
            customerManagementService.addCustomer(customer.getId(), customer);
        } catch (Exception e){
            Response r = WebClient.client(customerManagementService).getResponse();
            log.info(String.valueOf(r.getStatus()));
            throw new SoapFault(String.valueOf(r.getStatus()),new QName(String.valueOf(r.getStatus())));
        }
    }

    @Override
    public void delete(String id) {

        try {
           customerManagementService.deleteCustomer(id);
        } catch (WebApplicationException wea) {

            throw new UnknownCustomerFault();
        }  
    }

    @Override
    public void post(Customer customer) {

        try {
           customerManagementService.updateCustomer(customer.getId(), customer);
        } catch (WebApplicationException wea) {

            throw new UnknownCustomerFault();
        }
    }

}
