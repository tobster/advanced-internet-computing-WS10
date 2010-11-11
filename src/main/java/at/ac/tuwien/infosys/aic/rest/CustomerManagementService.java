package at.ac.tuwien.infosys.aic.rest;

import at.ac.tuwien.infosys.aic.model.Customer;
import at.ac.tuwien.infosys.aic.store.DataStore;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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
            throw new WebApplicationException(404);
        }
    }
    /*
    //handlePut -> Customer hinzufuegen
    @PUT
    @Path("/customers/{id}")
    @Consumes("application/json")
    public Response addCustomer(@PathParam("id") Long id, Customer customer) {
    //Customer hinzufuegen
    return null;
    }

    //handlePost -> Customer aktualisieren
    @POST
    @Path("/customers")
    @Consumes("application/json")
    public Response updateCustomer(Customer customer) {
    //Customer aktualisieren
    return null;
    }

    //update_account just takes the customer and adds the changedValue parameter to the customer's open balance
    @Path("/customers")
    public Response update_account(@PathParam("id") String id, @PathParam("changedValue") BigDecimal changedValue) {
    //Customer aktualisieren
    return null;
    }

    //notify (customer and message, as string)
    //TODO
    */
    //handleDelete -> Customer loeschen
    @DELETE
    @Path("/{id}")
    public void deleteCustomer(@PathParam("id") String id) {
        log.info("deletCustomer called!");
        Customer result = ds.getCustomer(id);
        if (!ds.deleteCustomer(id)) {
             throw new WebApplicationException(404);
        }
    }
}
