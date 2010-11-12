package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Customer;
import java.math.BigDecimal;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.QueryParam;

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
     //update_account
     public void update_account(@WebParam(name = "id") String id, @QueryParam(value = "changedValue") BigDecimal changedValue);
     //notify_customer
     public void notify(@WebParam(name = "id") String id, String message);

}
