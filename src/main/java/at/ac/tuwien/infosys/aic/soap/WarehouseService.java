/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Product;
import java.math.BigDecimal;
import javax.jws.WebParam;

public interface WarehouseService extends SupplierServices {

    WarehouseResponse check_availability(@WebParam(name = "product") Product product, @WebParam(name = "amount") Integer amount);

}
