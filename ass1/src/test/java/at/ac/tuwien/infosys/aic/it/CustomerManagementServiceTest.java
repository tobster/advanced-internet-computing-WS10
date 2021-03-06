/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import at.ac.tuwien.infosys.aic.rest.CustomerManagementService;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpPost;
import at.ac.tuwien.infosys.aic.model.Customer;
import org.apache.http.util.EntityUtils;
import javax.ws.rs.core.UriBuilder;
import at.ac.tuwien.infosys.aic.store.DataStore;
import static at.ac.tuwien.infosys.aic.Constants.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author smolle
 */
public class CustomerManagementServiceTest extends BaseIntegrationTest {

    //private DataStore ds = DataStore.getInstance();
    private HttpClient httpclient = new DefaultHttpClient();
    private CustomerManagementService customerManagementService;

    public CustomerManagementServiceTest() {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").build();
        customerManagementService = JAXRSClientFactory.create( uri.toString() , CustomerManagementService.class);
        WebClient.client(customerManagementService).accept("application/json");
    }




    @Test
    public void testGetExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(200));
        HttpEntity entity = response.getEntity();
        assertNotNull(entity);
        String result = EntityUtils.toString(entity);
        assertThat(result, containsString(CUSTOMER1));
        assertThat(result, containsString(customerManagementService.getCustomer(CUSTOMER1).getName()));
        //TODO more sophisticated assertions
    }

    @Test
    public void testGetNonExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("nix77070b-96f3-47ac-9fe9-dfe2dadc00cb").build();

        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testDeleteNonExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("nix77070b-96f3-47ac-9fe9-dfe2dadc00cb").build();

        HttpDelete request = new HttpDelete(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testDeleteExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER2).build();

        HttpDelete request = new HttpDelete(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(204));
        uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER2).build();
        HttpGet getRequest = new HttpGet(uri);
        response = httpclient.execute(getRequest);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testPutCustomerIDmissmatch() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).build();
        HttpPut request = new HttpPut(uri);
        StringEntity payload = new StringEntity("{\"customer\":{\"@id\":\"" + CUSTOMER2 + "\",\"name\":\"Hudrich Harrer\",\"openBalance\":10,\"adresses\":{\"@id\":\"a8888070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"street\":\"Mollardgasse\",\"city\":\"Wien\",\"house\":23,\"door\":1,\"zipCode\":1060,\"isShipping\":true,\"isBilling\":true,\"isOther\":true}}}");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(409));
    }

    @Test
    public void testPutNonExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("c4444070b-96f3-47ac-9fe9-dfe2dadc00cb").build();
        HttpPut request = new HttpPut(uri);
        StringEntity payload = new StringEntity("{\"customer\":{\"@id\":\"c4444070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"name\":\"Heinrich Harrer\",\"openBalance\":0,\"adresses\":{\"@id\":\"a8888070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"street\":\"Mollardgasse\",\"city\":\"Wien\",\"house\":23,\"door\":1,\"zipCode\":1060,\"isShipping\":true,\"isBilling\":true,\"isOther\":true}}}");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(201));

        Customer customer = customerManagementService.getCustomer("c4444070b-96f3-47ac-9fe9-dfe2dadc00cb");
        assertNotNull(customer);
        assertThat(customer.getId(), is("c4444070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        assertThat(customer.getName(), is("Heinrich Harrer"));
        assertThat(customer.getOpenBalance(), is(BigDecimal.ZERO));
        assertThat(customer.getAdresses().get(0).getId(), is("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb"));
    }

    @Test
    public void testPutExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).build();
        HttpPut request = new HttpPut(uri);
        StringEntity payload = new StringEntity("{\"customer\":{\"@id\":\"" + CUSTOMER1 + "\",\"name\":\"Hudrich Harrer\",\"openBalance\":10,\"adresses\":{\"@id\":\"a8888070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"street\":\"Mollardgasse\",\"city\":\"Wien\",\"house\":23,\"door\":1,\"zipCode\":1060,\"isShipping\":true,\"isBilling\":true,\"isOther\":true}}}");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(204));
        Customer customer = customerManagementService.getCustomer(CUSTOMER1);
        assertNotNull(customer);
        assertThat(customer.getId(), is(CUSTOMER1));
        assertThat(customer.getName(), is("Hudrich Harrer"));
        assertThat(customer.getOpenBalance(), is(BigDecimal.TEN));
        assertThat(customer.getAdresses().get(0).getId(), is("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb"));
    }

    @Test
    public void testPostNonExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("c4434070b-96f3-47ac-9fe9-dfe2dadc00cb").build();
        HttpPost request = new HttpPost(uri);
        StringEntity payload = new StringEntity("{\"customer\":{\"@id\":\"c4434070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"name\":\"Heinrich Harrer\",\"openBalance\":0,\"adresses\":{\"@id\":\"a8888070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"street\":\"Mollardgasse\",\"city\":\"Wien\",\"house\":23,\"door\":1,\"zipCode\":1060,\"isShipping\":true,\"isBilling\":true,\"isOther\":true}}}");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testPostCustomerIDmissmatch() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).build();
        HttpPost request = new HttpPost(uri);
        StringEntity payload = new StringEntity("{\"customer\":{\"@id\":\"" + CUSTOMER2 + "\",\"name\":\"Hudrich Harrer\",\"openBalance\":10,\"adresses\":{\"@id\":\"a8888070b-96f3-47ac-9fe9-dfe2dadc00cb\",\"street\":\"Mollardgasse\",\"city\":\"Wien\",\"house\":23,\"door\":1,\"zipCode\":1060,\"isShipping\":true,\"isBilling\":true,\"isOther\":true}}}");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(409));
    }

    @Test
    public void testPostExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).build();
        HttpPost request = new HttpPost(uri);
        StringEntity payload = new StringEntity("{\"customer\":{\"@id\":\"" + CUSTOMER1 + "\",\"name\":\"Hudrich Harrer\"}}");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(204));
        Customer customer = customerManagementService.getCustomer(CUSTOMER1);
        assertNotNull(customer);
        assertThat(customer.getId(), is(CUSTOMER1));
        assertThat(customer.getName(), is("Hudrich Harrer"));
        assertThat(customer.getOpenBalance(), is(BigDecimal.ZERO));
        assertThat(customer.getAdresses().get(0).getId(), is("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb"));
    }

    @Test
    public void testUpdateAccountNonExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer")
                .path("c4434070b-96f3-47ac-9fe9-dfe2dadc00cb")
                .path("account")
                .queryParam("changedValue", 5)
                .build();
        HttpPost request = new HttpPost(uri);
        request.setURI(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testUpdateAccountExistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).path("account").queryParam("changedValue", "5.0").build();
        System.out.println(uri);
        HttpPost request = new HttpPost(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(204));
        Customer customer = customerManagementService.getCustomer(CUSTOMER1);
        assertNotNull(customer);
        assertThat(customer.getId(), is(CUSTOMER1));
        assertThat(customer.getOpenBalance(), is(new BigDecimal("15")));
    }

    @Test
    public void testNotifyExistingCustomer()  throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).path("notify").build();
        HttpPost request = new HttpPost(uri);
        StringEntity payload = new StringEntity("{\"message\": \"John\" }");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(204));

    }

    @Test
    public void testNotifyNonExistingCustomer()  throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("WRONGID").path("notify").build();
        HttpPost request = new HttpPost(uri);
        StringEntity payload = new StringEntity("{\"message\": \"John\" }");
        payload.setContentType("application/json");
        request.setEntity(payload);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));

    }
}
