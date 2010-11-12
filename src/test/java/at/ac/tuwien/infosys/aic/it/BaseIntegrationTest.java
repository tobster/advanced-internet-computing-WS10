/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import static at.ac.tuwien.infosys.aic.Constants.*;
import at.ac.tuwien.infosys.aic.server.Server;
import at.ac.tuwien.infosys.aic.soap.ResetService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author smolle
 */
public abstract class BaseIntegrationTest {

    private ResetService resetService;

    public BaseIntegrationTest() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ResetService.class);
        factory.setAddress(RESETADDRESS);
        resetService = (ResetService) factory.create();
    }

    @Before
    public void reset() {
        resetService.reset();
    }
}
