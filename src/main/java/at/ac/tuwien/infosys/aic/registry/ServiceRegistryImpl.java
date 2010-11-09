/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.registry;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/registry",
portName = "RegistryPT",
endpointInterface = "at.ac.tuwien.infosys.aic.soap.ServiceRegistry")
public class ServiceRegistryImpl implements ServiceRegistry {

    DataStore ds = DataStore.getInstance();
    Logger log = Logger.getLogger("ServiceRegistryImpl");

    @Override
    public W3CEndpointReference getSupplier(Product product) {

        log.info("Service Registry called!");

        //return ds.

    }

}
