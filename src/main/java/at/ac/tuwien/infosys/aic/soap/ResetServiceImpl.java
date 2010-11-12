package at.ac.tuwien.infosys.aic.soap;

import at.ac.tuwien.infosys.aic.store.DataStore;
import javax.jws.WebService;

@WebService(endpointInterface = "at.ac.tuwien.infosys.aic.soap.ResetService")
public class ResetServiceImpl implements ResetService {

    private DataStore ds = DataStore.getInstance();

    @Override
    public void reset() {
        ds.init();
    }
}
