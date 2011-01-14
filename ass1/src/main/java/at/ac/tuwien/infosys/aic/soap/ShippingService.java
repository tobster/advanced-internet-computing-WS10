package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownAddressFault;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService//(portName = "ShippingPT", name = "Shipping",
(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/shipping")
public interface ShippingService {

    String ship_items(@WebParam(name = "items") Item[] items, @WebParam(name = "address") Address address)throws UnknownProductFault, UnknownAddressFault;
}
