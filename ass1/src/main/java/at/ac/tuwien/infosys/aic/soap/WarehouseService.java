/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Product;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService//(portName = "WarehousePT", name = "Warehouse",
(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/warehouse")
public interface WarehouseService extends SupplierService {

    WarehouseResponse check_availability(@WebParam(name = "product") Product product, @WebParam(name = "amount") Integer amount);
}
