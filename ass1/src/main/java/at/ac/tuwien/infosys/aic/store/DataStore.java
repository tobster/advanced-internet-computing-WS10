/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.store;

import static at.ac.tuwien.infosys.aic.Constants.*;
import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Customer;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.model.Order;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.soap.ProductData;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    private static final DataStore instance = new DataStore();
    private Map<String, Address> addresses;
    private Map<String, Customer> customers;
    private Map<String, Order> orders;
    private Map<String, Product> products;
    private Map<Product, ProductData> availability;
    private Map<Product, String> productEndpointAddresses;
    private Map<String, LinkedList<String>> messages;


    public void init() {
        addresses = new ConcurrentHashMap<String, Address>();
        customers = new ConcurrentHashMap<String, Customer>();
        orders = new ConcurrentHashMap<String, Order>();
        products = new ConcurrentHashMap<String, Product>();
        availability = new ConcurrentHashMap<Product, ProductData>();
        productEndpointAddresses = new ConcurrentHashMap<Product, String>();
        messages = new ConcurrentHashMap<String, LinkedList<String>>();

        //      Products
        Product p = new Product();
        p.setId("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        p.setName("Moby Dick");
        p.setSingleUnitPrice(BigDecimal.TEN);
        products.put(p.getId(), p);
        p = new Product();
        p.setId("aec0737d-e783-4c16-9b26-66040caf4aff");
        p.setName("War and Peace");
        p.setSingleUnitPrice(new BigDecimal(3));
        products.put(p.getId(), p);
        p = new Product();
        p.setId("xac0737d-e987-4c16-9b26-660406fgs41f");
        p.setName("1984");
        p.setSingleUnitPrice(new BigDecimal(7));
        products.put(p.getId(), p);
        //      Address
        Address a = new Address();
        a.setId("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb");
        a.setCity("Wien");
        a.setDoor(1);
        a.setHouse(23);
        a.setIsBilling(true);
        a.setIsOther(true);
        a.setIsShipping(true);
        a.setStreet("Mollardgasse");
        a.setZipCode("1060");
        addresses.put(a.getId(), a);
        a = new Address();
        a.setId("a9999070b-96f3-47ac-9fe9-dfe2dadc00cb");
        a.setCity("Wien");
        a.setHouse(6);
        a.setIsBilling(false);
        a.setIsOther(false);
        a.setIsShipping(false);
        a.setStreet("Mühlgasse");
        a.setZipCode("1040");
        addresses.put(a.getId(), a);
        //      Customer
        Customer c = new Customer();
        c.setId(CUSTOMER1);
        List ad = new ArrayList();
        ad.add(addresses.get("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        c.setAdresses(ad);
        c.setName("Heinrich Harrer");
        c.setOpenBalance(BigDecimal.TEN);
        customers.put(c.getId(), c);
        c = new Customer();
        c.setId(CUSTOMER2);
        ad = new ArrayList();
        ad.add(addresses.get("a9999070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        c.setAdresses(ad);
        c.setName("Heinrich Harrer");
        c.setOpenBalance(BigDecimal.ZERO);
        customers.put(c.getId(), c);
        //      Order
        Order o = new Order();
        o.setId("o7777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        o.setOrderDate(new Date());
        o.setCustomer(customers.get(CUSTOMER1));
        Item i = new Item();
        i.setProduct(products.get("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        i.setQuantity(21);
        List it = new ArrayList();
        it.add(i);
        o.setItems(it);
        orders.put("o7777070b-96f3-47ac-9fe9-dfe2dadc00cb", o);
        o = new Order();
        o.setId("o8888070b-96f3-47ac-9fe9-dfe2dadc00cb");
        o.setOrderDate(new Date());
        o.setCustomer(customers.get(CUSTOMER2));
        i = new Item();
        i.setProduct(products.get("aec0737d-e783-4c16-9b26-66040caf4aff"));
        i.setQuantity(23);
        it = new ArrayList();
        it.add(i);
        o.setItems(it);
        orders.put("o8888070b-96f3-47ac-9fe9-dfe2dadc00cb", o);
        //Warehouse
        ProductData pd = new ProductData();
        pd.setDeliveryTime(5);
        pd.setAmount(10);
        availability.put(products.get("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"), pd);
        pd = new ProductData();
        pd.setDeliveryTime(5);
        pd.setAmount(5);
        availability.put(products.get("aec0737d-e783-4c16-9b26-66040caf4aff"), pd);
        pd = new ProductData();
        pd.setDeliveryTime(5);
        pd.setAmount(0);
        availability.put(products.get("xac0737d-e987-4c16-9b26-660406fgs41f"), pd);
        //Service registry
        productEndpointAddresses.put(products.get("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"), SUPPLIER1ADDRESS);
        productEndpointAddresses.put(products.get("aec0737d-e783-4c16-9b26-66040caf4aff"), SUPPLIER2ADDRESS);
        productEndpointAddresses.put(products.get("xac0737d-e987-4c16-9b26-660406fgs41f"), SUPPLIER2ADDRESS);
    }

    public String putProductEndpointAddress(Product key, String value) {
        return productEndpointAddresses.put(key, value);
    }

    public String getProductEndpointAddress(Object key) {
        return productEndpointAddresses.get(key);
    }

    public static DataStore getInstance() {
        return instance;
    }

    private DataStore() {
        init();
    }

    public Address putAddress(String key, Address value) {
        return addresses.put(key, value);
    }

    public Address getAddress(String key) {
        return addresses.get(key);
    }

    public Customer putCustomer(String key, Customer value) {
        return customers.put(key, value);
    }

    public boolean deleteCustomer(String key) {
        return customers.remove(key) != null ? true : false;
    }

    public Customer getCustomer(String key) {
        return customers.get(key);
    }

    public Order putOrder(String key, Order value) {
        return orders.put(key, value);
    }

    public Order getOrder(String key) {
        return orders.get(key);
    }

    public Product putProduct(String key, Product value) {
        return products.put(key, value);
    }

    public Product getProduct(String key) {

        return products.get(key);
    }

    public ProductData putProductData(Product p, ProductData pd) {
        return availability.put(p, pd);
    }

    public ProductData getProductData(Product p) {
        return availability.get(p);
    }

    public LinkedList<String> putMessages(String key, LinkedList<String> value) {
        return messages.put(key, value);
    }

    public LinkedList<String> getMessages(Object key) {
        return messages.get(key);
    }


}
