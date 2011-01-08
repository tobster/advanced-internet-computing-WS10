/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap.faults;

import org.apache.cxf.binding.soap.SoapFault;

@SuppressWarnings("serial")
public class UnknownCustomerFault extends SoapFault {

    public UnknownCustomerFault() {
        super("unknown costumer fault", ATTACHMENT_IO);



    }

    public UnknownCustomerFault(String msg) {
        super("unknown costumer fault", ATTACHMENT_IO);



    }

}
