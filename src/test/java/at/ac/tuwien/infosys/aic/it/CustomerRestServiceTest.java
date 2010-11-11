/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import org.apache.http.util.EntityUtils;
import javax.ws.rs.core.UriBuilder;
import at.ac.tuwien.infosys.aic.store.DataStore;
import static at.ac.tuwien.infosys.aic.Constants.*;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author smolle
 */
public class CustomerRestServiceTest extends BaseIntegrationTest {

    private DataStore ds = DataStore.getInstance();
    private HttpClient httpclient = new DefaultHttpClient();

    @Test
    public void testGetExsistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER1).build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(200));
        HttpEntity entity = response.getEntity();
        assertNotNull(entity);
        String result = EntityUtils.toString(entity);
        assertThat(result, containsString(CUSTOMER1));
        assertThat(result, containsString(ds.getCustomer(CUSTOMER1).getName()));
        //TODO more suffisticated assertions
    }

    @Test
    public void testGetNonExsistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("nix77070b-96f3-47ac-9fe9-dfe2dadc00cb").build();
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testDeleteNonExsistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path("nix77070b-96f3-47ac-9fe9-dfe2dadc00cb").build();
        HttpClient httpclient = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }

    @Test
    public void testDeleteExsistingCustomer() throws Exception {
        URI uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER2).build();
        HttpClient httpclient = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(uri);
        HttpResponse response = httpclient.execute(request);
        assertThat(response.getStatusLine().getStatusCode(), is(204));
        uri = UriBuilder.fromUri(CUSTOMERMANAGEMENT).path("Customer").path(CUSTOMER2).build();
        HttpGet getRequest = new HttpGet(uri);
        response = httpclient.execute(getRequest);
        assertThat(response.getStatusLine().getStatusCode(), is(404));
    }


}
