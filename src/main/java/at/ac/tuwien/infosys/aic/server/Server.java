package at.ac.tuwien.infosys.aic.server;

import at.ac.tuwien.infosys.aic.registry.ServiceRegistry;
import at.ac.tuwien.infosys.aic.registry.ServiceRegistryImpl;
import static at.ac.tuwien.infosys.aic.Constants.*;
import at.ac.tuwien.infosys.aic.soap.ShippingService;
import at.ac.tuwien.infosys.aic.soap.ShippingServiceImpl;
import at.ac.tuwien.infosys.aic.soap.SupplierImpl;
import at.ac.tuwien.infosys.aic.soap.SupplierService;
import at.ac.tuwien.infosys.aic.soap.WarehouseService;
import at.ac.tuwien.infosys.aic.soap.WarehouseServiceImpl;
import java.util.LinkedList;
import java.util.List;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {

    private List<org.apache.cxf.endpoint.Server> servers = new LinkedList<org.apache.cxf.endpoint.Server>();
    private LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
    private LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
    JaxWsServerFactoryBean svrFactory;
    public Server() {
        
        startService(svrFactory, ShippingService.class, "http://localhost:8080/Shipping", new ShippingServiceImpl());
        startService(svrFactory, SupplierService.class, SUPPLIER1ADDRESS, new SupplierImpl());
        startService(svrFactory, SupplierService.class, SUPPLIER2ADDRESS, new SupplierImpl());
        startService(svrFactory, WarehouseService.class, "http://localhost:8080/Warehouse", new WarehouseServiceImpl());
        startService(svrFactory, ServiceRegistry.class, REGISTRYADDRESS, new ServiceRegistryImpl());
    }

    private void startService(JaxWsServerFactoryBean svrFactory, Class iface, String address, Object implementation) {
        svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(iface);
        svrFactory.setAddress(address);
        svrFactory.setServiceBean(implementation);
        //svrFactory.getInInterceptors().add(loggingInInterceptor);
        //svrFactory.getOutInterceptors().add(loggingOutInterceptor);
        servers.add(svrFactory.create());
    }

    public static void main(String args[]) {
        new Server();
    }

    public void stop() {
        for (org.apache.cxf.endpoint.Server server : servers) {
            server.stop();
        }
    }
}
