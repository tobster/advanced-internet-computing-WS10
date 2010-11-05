/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.store;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Customer;
import at.ac.tuwien.infosys.aic.model.Order;
import at.ac.tuwien.infosys.aic.model.Product;
import java.util.Map;
import java.util.UUID;
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

        Product p = new Product();
        p.setId("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        p.setName("Moby Dick");
        products.put(p.getId(), p);
        p = new Product();
        p.setId("aec0737d-e783-4c16-9b26-66040caf4aff");
        p.setName("War and Peace");
        products.put(p.getId(), p);
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
