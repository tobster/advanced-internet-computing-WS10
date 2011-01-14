/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.soap.SupplierService", targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/supplier")
public class SupplierImpl implements SupplierService {

    Logger log = Logger.getLogger("aic23 Supplier Service");

    @Override
    @WebMethod(operationName = "order")
    @WebResult(targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/supplierService", name = "supplierServiceResult")
    public BigDecimal order(Product product, Integer amount) throws UnknownProductFault {

        log.info("supplier service called!");

        Product p = DataStore.getInstance().getProduct(product.getId());
        if (p == null) {
            throw new UnknownProductFault();
        }

        log.info("product name: " + p.getName());

        BigDecimal order = p.getSingleUnitPrice().multiply(new BigDecimal(amount));

        log.info("order total: " + order);

        return order;

    }
}
