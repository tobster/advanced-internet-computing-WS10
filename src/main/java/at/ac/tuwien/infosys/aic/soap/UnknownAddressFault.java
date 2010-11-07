/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import org.apache.cxf.binding.soap.SoapFault;

/**
 *
 * @author Christoph Derndorfer
 */
@SuppressWarnings("serial")
public class UnknownAddressFault extends SoapFault {

        public UnknownAddressFault() {
        super("unknown address fault", ATTACHMENT_IO);



    }

}
