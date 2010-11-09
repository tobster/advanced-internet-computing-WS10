/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
import javax.jws.WebService;

@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/supplier",
portName = "SupplierPT",
endpointInterface = "at.ac.tuwien.infosys.aic.soap.SupplierServices")
public class Supplier2 implements SupplierService {

    @Override
    public BigDecimal order(Product product, Integer amount) {

        Product p = DataStore.getInstance().getProduct(product.getId());
        if (p == null){
            throw new UnknownProductFault();
        }
        BigDecimal order = p.getSingleUnitPrice().multiply( new BigDecimal(amount));

        return order;

    }

}
