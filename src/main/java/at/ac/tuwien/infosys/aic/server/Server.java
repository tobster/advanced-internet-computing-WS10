package at.ac.tuwien.infosys.aic.server;

import at.ac.tuwien.infosys.aic.registry.ServiceRegistry;
import at.ac.tuwien.infosys.aic.registry.ServiceRegistryImpl;
import at.ac.tuwien.infosys.aic.rest.CustomerManagementService;
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
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {

    private List<org.apache.cxf.endpoint.Server> servers = new LinkedList<org.apache.cxf.endpoint.Server>();
    private LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
    private LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();

    public Server() {

        this.addShutdownHook();
        // SOAP
        startService(ShippingService.class, SHIPPINGADDRESS, new ShippingServiceImpl());
        startService(SupplierService.class, SUPPLIER1ADDRESS, new SupplierImpl());
        startService(SupplierService.class, SUPPLIER2ADDRESS, new SupplierImpl());
        startService(WarehouseService.class, WAREHOUSEADDRESS, new WarehouseServiceImpl());
        startService(ServiceRegistry.class, REGISTRYADDRESS, new ServiceRegistryImpl());

        //REST
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setAddress(CUSTOMERMANAGEMENT);
        sf.setServiceBeans(new CustomerManagementService());
        sf.getInInterceptors().add(loggingInInterceptor);
        sf.getOutInterceptors().add(loggingOutInterceptor);
        servers.add(sf.create());
    }

    private void startService(Class iface, String address, Object implementation) {
        JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
        svrFactory.setServiceClass(iface);
        svrFactory.setAddress(address);
        svrFactory.setServiceBean(implementation);
        svrFactory.getInInterceptors().add(loggingInInterceptor);
        svrFactory.getOutInterceptors().add(loggingOutInterceptor);
        servers.add(svrFactory.create());
    }

    public static void main(String args[]) {
        new Server();
    }

    private void addShutdownHook() {

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.out.println("Shutting down...");
                stopMe();
            }
        });
    }

    public void stopMe() {
        for (org.apache.cxf.endpoint.Server server : servers) {
            server.stop();
        }
    }
}
