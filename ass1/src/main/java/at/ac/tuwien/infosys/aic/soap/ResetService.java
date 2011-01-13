package at.ac.tuwien.infosys.aic.soap;

import javax.jws.WebService;

@WebService//(portName = "ShippingPT", name = "Shipping",
(targetNamespace = "http://infosys.tuwien.ac.at/aic10/dto/reset")
public interface ResetService {

    void reset();

}
