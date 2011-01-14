package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.soap.faults.NegativeAmountFault;
import at.ac.tuwien.infosys.aic.soap.faults.UnknownProductFault;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/warehouse",
portName = "WarehousePT",
endpointInterface = "at.ac.tuwien.infosys.aic.soap.WarehouseService")
public class WarehouseServiceImpl implements WarehouseService {

    DataStore ds = DataStore.getInstance();
    Logger log = Logger.getLogger("aic23 WarehouseServiceImpl");

    @Override
    @WebMethod(operationName = "order")
    @WebResult(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/warehouse", name = "warehouseOrderResult")
    public BigDecimal order(Product product, Integer amount) {

        log.info("warehouse service called!");

        Product p = ds.getProduct(product.getId());
        if (p == null){
            throw new UnknownProductFault();
        }

        log.info("product ordered: " + p.getName());

        ProductData pd = ds.getProductData(product);

        log.info("quantity of: " + p.getName() + " before processing the order: " + pd.getAmount());

        pd.setAmount(pd.getAmount() - amount);
        ds.putProductData(product, pd);

        log.info("quantity of: " + p.getName() + " after processing the order: " + pd.getAmount());

        BigDecimal order = p.getSingleUnitPrice().multiply( new BigDecimal(amount));

        log.info("order total: " + order);

        return order;
    }

    @Override
    public WarehouseResponse check_availability(Product product, Integer amount) {

        ProductData pd = ds.getProductData(product);
        WarehouseResponse w = new WarehouseResponse();

        if (amount < 1) {

            throw new NegativeAmountFault();
            
        } else {

            if (pd == null) {
                throw new UnknownProductFault();
            } else {
                w.setDeliveryTime(pd.getDeliveryTime());

                if (amount > pd.getAmount()){
                    w.setIsAvailable(false);
                    log.info("Check availability: " + product.getName() + " is not available in the warehouse.");
                } else {
                    w.setIsAvailable(true);
                    log.info("Check availability: " + product.getName() + " is available in the warehouse.");
                }
            }
            ;
            return w;
        }

    }



}
