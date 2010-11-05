package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ShippingService {

    String ship_items(@WebParam(name = "items") Item[] items, @WebParam(name = "address") Address address);
}
