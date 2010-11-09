/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.registry;

import at.ac.tuwien.infosys.aic.model.Product;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.cxf.ws.addressing.WSAddressingFeature;

/**
 *
 * @author smolle
 */
@WebService(portName = "RegistryPT", name = "Registry", targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/registry")
public interface ServiceRegistry {

    WSAddressingFeature getSupplier(@WebParam(name = "product") Product product);

}
