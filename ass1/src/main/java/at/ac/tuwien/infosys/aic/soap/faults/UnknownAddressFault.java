/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap.faults;

import javax.xml.ws.WebFault;
import org.apache.cxf.binding.soap.SoapFault;

/**
 *
 * @author Christoph Derndorfer
 */

@WebFault
public class UnknownAddressFault extends SoapFault {

        public UnknownAddressFault() {
        super("unknown address fault", ATTACHMENT_IO);



    }

}
