/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap.faults;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;

@WebFault(name="UnknownCustomerFault")
@XmlAccessorType( XmlAccessType.FIELD )
public class UnknownCustomerFault extends Exception {

    public UnknownCustomerFault() {
        super("unknown costumer fault");



    }

}
