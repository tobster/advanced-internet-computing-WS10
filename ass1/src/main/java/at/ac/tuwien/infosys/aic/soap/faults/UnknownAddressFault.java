/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap.faults;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebFault;
/**
 *
 * @author Christoph Derndorfer
 */

@WebFault(name="UnknownAddressFault")
@XmlAccessorType( XmlAccessType.FIELD )
public class UnknownAddressFault extends Exception {

        public UnknownAddressFault() {
        super("unknown address fault");



    }

}
