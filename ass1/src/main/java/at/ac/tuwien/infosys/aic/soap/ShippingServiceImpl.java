package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.soap.faults.UnknownAddressFault;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.soap.ShippingService" ,targetNamespace="http://infosys.tuwien.ac.at/aic10/dto/shipping")
public class ShippingServiceImpl implements ShippingService  {

    DataStore ds = DataStore.getInstance();
    Logger log = Logger.getLogger("aic23 Shipping Service");

    @Override
    @WebMethod(operationName = "ship_items")
    @WebResult(targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/shippingService", name = "shippingServiceSResult")
    public String ship_items(Item[] items, Address address) throws UnknownProductFault, UnknownAddressFault{
        log.info("ship items called!");

        if (address == null) {
            log.log(Level.WARNING, "address was null");
            throw new UnknownAddressFault();
        }

        StringBuffer message = new StringBuffer("Sending items ");
        if (items == null) {
            log.log(Level.WARNING, "item was null");
            throw new UnknownProductFault();
        }
        for (Item item : items) {
            if (item.getProduct() != null) {
                message.append("'");
                message.append(ds.getProduct(item.getProduct().getId())); //TODO
                message.append("', ");
            } else {
                log.log(Level.WARNING, "product was null");
                throw new UnknownProductFault();
            }
        }
        message.append("to ");

        if (ds.getAddress(address.getId()) != null) {

            Address thisAddress = DataStore.getInstance().getAddress(address.getId());

            message.append(thisAddress.getStreet() + " "
                    + thisAddress.getHouse() + ", "
                    + thisAddress.getZipCode() + " "
                    + thisAddress.getCity());
        } else {
            log.log(Level.WARNING, "address fault");
            throw new UnknownAddressFault();
        }

        log.info(message.toString());
        return UUID.randomUUID().toString();
    }
}
