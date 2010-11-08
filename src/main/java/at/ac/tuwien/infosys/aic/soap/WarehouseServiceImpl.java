/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import javax.jws.WebService;

@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/warehouse",
portName = "WarehousePT",
endpointInterface = "at.ac.tuwien.infosys.aic.soap.WarehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    Logger log = Logger.getLogger("Supplier Service");

    Map<Product, WarehouseResponse> availability = new ConcurrentHashMap<Product, WarehouseResponse>();

    @Override
    public BigDecimal order(Product product, Integer amount) {

        log.info("supplier service called!");

        Product p = DataStore.getInstance().getProduct(product.getId());
        if (p == null){
            throw new UnknownProductFault();
        }

        log.info("product name: " + p.getName());

        BigDecimal order = p.getSingleUnitPrice().multiply( new BigDecimal(amount));

        log.info("order total: " + order);

        return order;
    }

    @Override
    public WarehouseResponse check_availability(Product product, Integer amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
