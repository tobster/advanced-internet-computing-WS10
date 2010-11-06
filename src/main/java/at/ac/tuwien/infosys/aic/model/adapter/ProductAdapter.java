/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.ac.tuwien.infosys.aic.model.adapter;

import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.store.DataStore;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class ProductAdapter extends XmlAdapter<String, Product>{

    @Override
    public Product unmarshal(String v) throws Exception {
            return DataStore.getInstance().getProduct(v);
        }

    @Override
    public String marshal(Product v) throws Exception {
        return v.getId();
        }

}
