/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.ac.tuwien.infosys.aic.store;

import at.ac.tuwien.infosys.aic.model.Address;
import at.ac.tuwien.infosys.aic.model.Customer;
import at.ac.tuwien.infosys.aic.model.Item;
import at.ac.tuwien.infosys.aic.model.Order;
import at.ac.tuwien.infosys.aic.model.Product;
import at.ac.tuwien.infosys.aic.soap.ProductData;
import at.ac.tuwien.infosys.aic.soap.WarehouseResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {

    private static DataStore instance = new DataStore();

    Map<String, Address> adresses = new ConcurrentHashMap<String, Address>();
    Map<String, Customer> customers = new ConcurrentHashMap<String, Customer>();
    Map<String, Order> orders = new ConcurrentHashMap<String, Order>();
    Map<String, Product> products = new ConcurrentHashMap<String, Product>();
    Map<Product, ProductData> availability = new ConcurrentHashMap<Product, ProductData>();


    public static DataStore getInstance() {
        if (instance == null) {
            synchronized(DataStore.class) {
                instance = new DataStore();
            }
        }
        return instance;
    }

    private DataStore() {


//      Procucts
        Product p = new Product();
        p.setId("a777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        p.setName("Moby Dick");
        p.setSingleUnitPrice(BigDecimal.ZERO);
        products.put(p.getId(), p);
        p = new Product();
        p.setId("aec0737d-e783-4c16-9b26-66040caf4aff");
        p.setName("War and Peace");
        p.setSingleUnitPrice(BigDecimal.TEN);
        products.put(p.getId(), p);

//      Adress
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
        adresses.put(a.getId(), a);
        a = new Address();
        a.setId("a9999070b-96f3-47ac-9fe9-dfe2dadc00cb");
        a.setCity("Wien");
        a.setHouse(6);
        a.setIsBilling(false);
        a.setIsOther(false);
        a.setIsShipping(false);
        a.setStreet("Mühlgasse");
        a.setZipCode("1040");
        adresses.put(a.getId(), a);

//      Customer
        Customer c = new Customer();
        c.setId("c7777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        List ad = new ArrayList();
        ad.add(adresses.get("a8888070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        c.setAdresses(ad);
        c.setName("Heinrich Harrer");
        c.setOpenBalance(BigDecimal.ZERO);
        customers.put("c7777070b-96f3-47ac-9fe9-dfe2dadc00cb", c);
        c = new Customer();
        c.setId("c8888070b-96f3-47ac-9fe9-dfe2dadc00cb");
        ad = new ArrayList();
        ad.add(adresses.get("a9999070b-96f3-47ac-9fe9-dfe2dadc00cb"));
        c.setAdresses(ad);
        c.setName("Heinrich Harrer");
        c.setOpenBalance(BigDecimal.ZERO);
        customers.put("c8888070b-96f3-47ac-9fe9-dfe2dadc00cb", c);

//      Order
        Order o = new Order();
        o.setId("o7777070b-96f3-47ac-9fe9-dfe2dadc00cb");
        o.setOrderDate(new Date());
        o.setCustomer(customers.get("c7777070b-96f3-47ac-9fe9-dfe2dadc00cb"));
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
        o.setCustomer(customers.get("c8888070b-96f3-47ac-9fe9-dfe2dadc00cb"));
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
        availability.put(products.get("a777070b-96f3-47ac-9fe9-dfe2dadc00cb"),pd);
        pd = new ProductData();
        pd.setDeliveryTime(5);
        pd.setAmount(5);
        availability.put(products.get("aec0737d-e783-4c16-9b26-66040caf4aff"),pd);
        

    }

    public Address putAddress(String key, Address value) {
        return adresses.put(key, value);
    }

    public Address getAddress(String key) {
        return adresses.get(key);
    }

    public Customer putCustomer(String key, Customer value) {
        return customers.put(key, value);
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
}