package at.ac.tuwien.infosys.aic.model;

import java.util.List;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.Unmarshaller;
import org.junit.Before;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBContext;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class XmlMarshallingTest {

    private Marshaller m;
    private Unmarshaller u;

    @Before
    public void setUp() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Order.class);
        m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        u = context.createUnmarshaller();
    }

    @Test
    public void marshallunmarshalOrder() throws Exception {
              Customer customer = new Customer();
        customer.setId("customerid");
        customer.setName("customername");
        customer.setOpenBalance(BigDecimal.ZERO);
        List<Address> adresses = new ArrayList<Address>();
        customer.setAdresses(adresses);
        Order testOrder = new Order();
        testOrder.setId("test");
        testOrder.setOrderDate(new Date(42L));
        List<Item> items = new ArrayList<Item>(1);
        Item item = new Item();
        item.setQuantity(21);
        Product product = new Product();
        product.setName("cheese");
        product.setId("productid");
        product.setSingleUnitPrice(BigDecimal.TEN);
        item.setProduct(product);
        items.add(item);
        testOrder.setItems(items);
        StringWriter stringWriter = new StringWriter();
        m.marshal(testOrder, stringWriter);
        String result = stringWriter.toString();
        System.out.println(result);
        assertThat(result, containsString("id=\"test\""));
        assertThat(result, containsString("orderDate>42"));
        assertThat(result, containsString("item"));
        assertThat(result, containsString("quantity"));
        assertThat(result, containsString("21"));
        assertThat(result, containsString("product"));
        assertThat(result, containsString("productid"));
        Order uOrder = (Order) u.unmarshal(new StringReader(result));
        assertThat(uOrder, equalTo(testOrder));
        assertThat(uOrder.getId(), is("test"));
        assertThat(uOrder.getOrderDate(), is(new Date(42L)));
    }

    @Test
    public void marshallunmarshalItem() throws Exception {
    }
}
