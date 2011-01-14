/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.soap.faults;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;


@WebFault(name="NegativeAmountFault")
@XmlAccessorType( XmlAccessType.FIELD )
public class NegativeAmountFault extends Exception {

    public NegativeAmountFault() {
        super("negative amount fault");



    }

}
