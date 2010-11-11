/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.it;

import at.ac.tuwien.infosys.aic.server.Server;
import at.ac.tuwien.infosys.aic.store.DataStore;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author smolle
 */
public abstract class BaseIntegrationTest {

    protected static Server server;

    @BeforeClass
    public static void intitServer() {
        server = new Server();
    }

    @AfterClass
    public static void stop() {
        if (server != null) {
            server.stopMe();
            server = null;
        }
    }
}
