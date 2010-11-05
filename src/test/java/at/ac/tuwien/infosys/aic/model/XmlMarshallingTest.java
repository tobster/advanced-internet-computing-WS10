package at.ac.tuwien.infosys.aic.model;

import java.io.StringReader;
import java.io.StringWriter;
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
        Order testOrder = new Order();
        testOrder.setId("test");
        testOrder.setOrderDate(new Date(42L));
        StringWriter stringWriter = new StringWriter();
        m.marshal(testOrder, stringWriter);
        String result = stringWriter.toString();
        System.out.println(result);
        assertThat(result, containsString("id=\"test\""));
        assertThat(result, containsString("orderDate>42"));
        Order uOrder = (Order) u.unmarshal(new StringReader(result));
        assertThat(uOrder, equalTo(testOrder));
        assertThat(uOrder.getId(), is("test"));
        assertThat(uOrder.getOrderDate(), is(new Date(42L)));
    }
}
