/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Customer;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(portName = "CustomerManagerServiceWrapperPT", name = "CustomManagerWrapper", targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/custommanagerwrapper")
public interface CustomerManagementServiceWrapper {

     //getCustomer
     public Customer get(@WebParam(name ="id") String id);
     //addCustomer
     public void put(@WebParam(name = "customer") Customer customer);
     //updateCustomer
     public void post(@WebParam(name = "customer") Customer customer);
     //deleteCustomer
     public void delete(@WebParam(name = "id") String id);

}
