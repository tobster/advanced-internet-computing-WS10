/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap.faults;

import javax.xml.ws.WebFault;
import org.apache.cxf.binding.soap.SoapFault;

@WebFault
public class NegativeAmountFault extends SoapFault {

    public NegativeAmountFault() {
        super("negative amount fault", ATTACHMENT_IO);



    }

}
