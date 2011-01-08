/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap.faults;

import org.apache.cxf.binding.soap.SoapFault;

@SuppressWarnings("serial")
public class NegativeAmountFault extends SoapFault {

    public NegativeAmountFault() {
        super("negative amount fault", ATTACHMENT_IO);



    }

}
