package sg.edu.nus.iss.pizzaappredo.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.util.UUID;


public class Order implements Serializable{

    private double totalCost = 0;
    private String orderId;
    private Pizza pizza;
    private Delivery delivery;

    public String generateId(){
        return UUID.randomUUID().toString().substring(0,8);
    }
    
    public Order(Pizza pizza, Delivery delivery) {
        this.pizza = pizza;
        this.delivery = delivery;
        this.orderId = generateId();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return this.getDelivery().getName();
    }

    public String getAddress() {
        return this.getDelivery().getAddress();
    }

    public String getPhone() {
        return this.getDelivery().getPhone();
    }

    public boolean getRush() {
        return this.getDelivery().isRush();
    }

    public String getComments() {
        return this.getDelivery().getComments();
    }

    public String getPizzaName() {
        return this.getPizza().getPizza();
    }

    public String getSize() {
        return this.getPizza().getSize();
    }

    public int getQuantity() {
        return this.getPizza().getQuantity();
    }
 // ? ->  shorthand operator to check if rush is true (if rush add $2 to get total cost, else get total cost)
    // public double getPizzaCost() {
    //     return this.getRush() ? this.getTotalCost() -2 : this.getTotalCost(); 
    // }

    public double getPizzaCost() {
        if(this.getRush()) {
            return this.getTotalCost() + 2;
        }
        return this.getTotalCost();
    }

    public String rushCharge() {
        if(this.getRush()) {
            return "$2";
        }
        return "N/A";
    }

    //create JsonObject that is being read by a json object reader
    //create json object reader that takes in String Json rby StringReader to read object
    public static Order create(String json) {
        JsonObject o = Json.createReader(new StringReader(json)).readObject();
        Pizza p = Pizza.create(o);
        Delivery d  = Delivery.create(o);
        Order order = new Order(p,d);
        order.setOrderId(o.getString("orderId"));
        order.setTotalCost(o.getJsonNumber("total").doubleValue());
        return order;
    }
    // create JsonObject builder to convert to json format
    // return created object, dd key valu pair
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
        .add("orderId", this.getOrderId())
        .add("name", this.getName())
        .add("address", this.getAddress())
        .add("phone", this.getPhone())
        .add("rush", this.getRush())
        .add("comments", this.getComments())
        .add("pizza", this.getPizzaName())
        .add("size", this.getSize())
        .add("quantity", this.getQuantity())
        .add("total", this.getTotalCost())
        .build();
        
    }
        
    
}
