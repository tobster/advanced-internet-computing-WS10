/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.store;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Customer;
import at.ac.tuwien.infosys.aic.model.Order;
import at.ac.tuwien.infosys.aic.model.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    private static DataStore instance = new DataStore();

    Map<String, Address> adresses = new ConcurrentHashMap<String, Address>();
    Map<String, Customer> customers = new ConcurrentHashMap<String, Customer>();
    Map<String, Order> order = new ConcurrentHashMap<String, Order>();
    Map<String, Product> products = new ConcurrentHashMap<String, Product>();

    public static DataStore getInstance() {
        return instance;
    }

    private DataStore() {
    }

    public Address putAddress(String key, Address value) {
        return adresses.put(key, value);
    }

    public Address getAddress(Object key) {
        return adresses.get(key);
    }

    public Customer putCustomer(String key, Customer value) {
        return customers.put(key, value);
    }

    public Customer getCustomer(Object key) {
        return customers.get(key);
    }

    public Order putOrder(String key, Order value) {
        return order.put(key, value);
    }

    public Order getOrder(Object key) {
        return order.get(key);
    }

    public Product putProduct(String key, Product value) {
        return products.put(key, value);
    }

    public Product getProduct(Object key) {
        return products.get(key);
    }
}
