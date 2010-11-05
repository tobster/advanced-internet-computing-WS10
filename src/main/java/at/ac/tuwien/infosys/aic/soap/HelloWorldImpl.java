
package at.ac.tuwien.infosys.aic.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "ac.at.tuwien.infosys.aic.soap.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

