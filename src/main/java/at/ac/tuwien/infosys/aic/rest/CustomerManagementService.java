package at.ac.tuwien.infosys.aic.rest;

import at.ac.tuwien.infosys.aic.model.Customer;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;

@Path("/Customer")
@Produces("application/json")
public class CustomerManagementService {

    DataStore ds = DataStore.getInstance();
    Logger log = Logger.getLogger("CustomerManagementService");

    //handleGet -> Customer Info holen
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Customer getCustomer(@PathParam("id") String id) {

        log.info("getCustomer called!");
        Customer result = ds.getCustomer(id);
        if (result != null) {
            return result;
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    //handlePut -> Customer hinzufuegen
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response addCustomer(@PathParam("id") String id, Customer customer) {
        log.info("putCustomer called!");
        if (!id.equals(customer.getId())) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        ResponseBuilder builder = new ResponseBuilderImpl();
        if (ds.getCustomer(id) != null) {
            builder.status(Response.Status.NO_CONTENT);
        } else {
            builder.status(Response.Status.CREATED);
        }
        ds.putCustomer(id, customer);
        return builder.build();

    }

    //handlePost -> Customer aktualisieren
    @POST
    @Path("{id}")
    @Consumes("application/json")
    public void updateCustomer(@PathParam("id") String id, Customer customer) {
        log.info("postCustomer called!");
        if (!id.equals(customer.getId())) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        Customer currentCustomer = ds.getCustomer(id);
        if (currentCustomer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        } else {
            if (customer.getName() != null) {
                currentCustomer.setName(customer.getName());
            }
            if (customer.getAdresses() != null) {
                currentCustomer.setAdresses(customer.getAdresses());
            }
            if (customer.getOpenBalance() != null) {
                currentCustomer.setOpenBalance(customer.getOpenBalance());
            }
        }
    }

    //update_account just takes the customer and adds the changedValue parameter to the customer's open balance
    @Path("/customers")
    public Response update_account(@PathParam("id") String id, @PathParam("changedValue") BigDecimal changedValue) {
        //Customer aktualisieren
        return null;
    }

    //notify (customer and message, as string)
    //TODO
    //handleDelete -> Customer loeschen
    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") String id) {
        log.info("deletCustomer called!");
        Customer result = ds.getCustomer(id);
        if (!ds.deleteCustomer(id)) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
