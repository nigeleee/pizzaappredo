package sg.edu.nus.iss.pizzaappredo.model;

import java.io.Serializable;

import org.json.JSONObject;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Pizza implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull (message = "Pizza name cannot be null")
    private String pizza;

    @NotNull(message = "Pizza size cannot be null")
    private String size;

    @NotNull(message = "Pizza quantity cannot be null")
    @Min(value = 1, message = "Pizza quantity cannot be less than 1")
    private int quantity;
    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Pizza [pizza=" + pizza + ", size=" + size + ", quantity=" + quantity + "]";
    }
//create Pizza java object by getting json values from json object
  public static Pizza create(JsonObject o) {
    Pizza p = new Pizza();
    p.setPizza(o.getString("pizza"));
    p.setSize(o.getString("size"));
    p.setQuantity(o.getInt("quantity"));
    return p;
  }
}
