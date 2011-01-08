/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.model.adapter;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<Long, Date> {

    @Override
    public Long marshal(Date v) throws Exception {
        return v.getTime();
    }

    @Override
    public Date unmarshal(Long v) throws Exception {
        return new Date(v);
    }
}
