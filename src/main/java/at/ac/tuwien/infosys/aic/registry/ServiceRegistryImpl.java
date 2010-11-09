/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.registry;

import at.ac.tuwien.infosys.aic.model.Product;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.jws.WebService;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/registry",
portName = "RegistryPT",
endpointInterface = "at.ac.tuwien.infosys.aic.soap.ServiceRegistry")
public class ServiceRegistryImpl implements ServiceRegistry {

    Map<Product, W3CEndpointReference> productSupplier = new ConcurrentHashMap<Product, W3CEndpointReference>();

    @Override
    public W3CEndpointReference getSupplier(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
