package at.ac.tuwien.infosys.aic.rest;

import at.ac.tuwien.infosys.aic.model.Customer;
import java.math.BigDecimal;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


public interface CustomerManagementService {

    @PUT
    @Path(value = "{id}")
    @Consumes(value = "application/json")
    Response addCustomer(@PathParam(value = "id")
    String id, Customer customer);

    @DELETE
    @Path(value = "/{id}")
    void deleteCustomer(@PathParam(value = "id")
    String id);

    @GET
    @Path(value = "/{id}")
    @Produces(value = "application/json")
    Customer getCustomer(@PathParam(value = "id")
    String id);

    @POST
    @Path(value = "{id}")
    @Consumes(value = "application/json")
    void updateCustomer(@PathParam(value = "id")
    String id, Customer customer);

    @POST
    @Path(value = "{id}/account")
    void update_account(@PathParam(value = "id")
    String id, @QueryParam(value = "changedValue")
    BigDecimal changedValue);

    @POST
    @Path(value = "{id}/notify")
    @Consumes(value = "application/json")
    void notify(@PathParam(value = "id")
    String id, String message);

}
