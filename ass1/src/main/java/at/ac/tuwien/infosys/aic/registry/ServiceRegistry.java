/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.registry;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

/**
 *
 * @author smolle, derndorfer
 */
@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/registry")//(portName = "RegistryPT", name = "Registry", targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/registry")
public interface ServiceRegistry  {

    EndpointReferenceType getSupplier(@WebParam(name = "product") Product product) throws UnknownProductFault;
}
