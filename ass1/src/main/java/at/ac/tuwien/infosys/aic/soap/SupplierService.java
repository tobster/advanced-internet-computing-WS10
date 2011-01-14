/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Product;
import java.math.BigDecimal;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/supplier")//(portName = "SupplierPT", name = "Supplier",
public interface SupplierService {

    // returns total Price
    BigDecimal order(@WebParam(name = "product") Product product, @WebParam(name = "amount") Integer amount);
}
