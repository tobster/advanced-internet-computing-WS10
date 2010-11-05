package at.ac.tuwien.infosys.aic.soap;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
    String sayHi(String text);
}

