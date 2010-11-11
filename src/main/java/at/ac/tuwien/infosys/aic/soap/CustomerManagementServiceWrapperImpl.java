/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import at.ac.tuwien.infosys.aic.model.Customer;
import static at.ac.tuwien.infosys.aic.Constants.*;

//@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.soap.CustomManagerServiceWrapper")
public class CustomerManagementServiceWrapperImpl implements CustomerManagementServiceWrapper {

    @Override
    public Customer get(String id) {

        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(id).build();
        Customer customer = JAXRSClientFactory.create( uri.toString() , Customer.class);  
        return customer;
        
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void post(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
