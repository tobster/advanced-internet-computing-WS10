/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.registry;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.xmlsoap.schemas.ws._2004._08.addressing.AttributedQName;
import org.xmlsoap.schemas.ws._2004._08.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;
import org.xmlsoap.schemas.ws._2004._08.addressing.ObjectFactory;

@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.registry.ServiceRegistry")
public class ServiceRegistryImpl implements ServiceRegistry {

    DataStore ds = DataStore.getInstance();
    Logger log = Logger.getLogger("ServiceRegistryImpl");

    @Override
    public EndpointReferenceType getSupplier(Product product) {

        log.info("Service Registry called!");

        ObjectFactory objectFactory = new ObjectFactory();
        EndpointReferenceType endpointReferenceType = objectFactory.createEndpointReferenceType();

        String endpointAddress = ds.getProductEndpointAddress(product);
        
        AttributedURI uri = new AttributedURI();
        uri.setValue(endpointAddress);
        endpointReferenceType.setAddress(uri);

        AttributedQName portType = new AttributedQName();
        portType.setValue(new QName("SupplierPT"));
        endpointReferenceType.setPortType(portType);

        //endpointReferenceType.setReferenceParameters(null);

        //endpointReferenceType.setReferenceProperties(null);

        //endpointReferenceType.setServiceName(null);

        if (endpointAddress != null) {            
            return endpointReferenceType;
        } else {
            throw new UnknownProductFault();
        }
       
        //W3CEndpointReferenceBuilder builder = new W3CEndpointReferenceBuilder();
        //String endpointAddress = ds.getProductEndpointAddress(product);
        //if (endpointAddress != null) {
        //    builder.address(endpointAddress);
        //    return builder.build();
        //} else {
        //    throw new UnknownProductFault();
        //}
    }
}
