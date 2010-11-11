/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.soap;

import javax.jws.WebService;
import at.ac.tuwien.infosys.aic.model.Customer;
import static at.ac.tuwien.infosys.aic.Constants.*;

//@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.soap.CustomManagerServiceWrapper")
public class CustomerManagementServiceWrapperImpl implements CustomerManagementServiceWrapper {

    @Override
    public Customer get(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void put(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void post(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
