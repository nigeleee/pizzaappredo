package sg.edu.nus.iss.pizzaappredo.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {

    @NotNull(message = "Delivery name cannot be null")
    @NotBlank(message = "Delivery name cannot be blank")
    @Size(min = 3, message="Delivery name must be at least 3 characters")
    private String name;

    @NotNull(message = "Delivery name cannot be null")
    @NotBlank(message = "Delivery name cannot be blank")
    private String address;

    @NotNull(message = "Delivery name cannot be null")
    @NotBlank(message = "Delivery name cannot be blank")
    @Pattern(regexp = "^[0-9]{8,}$", message="Delivery phone number must be 8 digits")
    private String phone;

    private boolean rush = false;
    private String comments;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    

    @Override
    public String toString() {
        return "Delivery [name=" + name + ", address=" + address + ", phone=" + phone + ", rush=" + this.rush + ", comments="
                + comments + "]";
    }
    public static Delivery create(JsonObject o) {
        Delivery d = new Delivery();
        d.setName(o.getString("name"));
        d.setAddress(o.getString("address"));
        d.setPhone(o.getString("phone"));
        d.setRush(o.getBoolean("rush"));
        d.setComments(o.getString("comments"));

        return d;

    }
    
}
