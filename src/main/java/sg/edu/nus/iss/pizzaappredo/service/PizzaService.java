package sg.edu.nus.iss.pizzaappredo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import sg.edu.nus.iss.pizzaappredo.model.Delivery;
import sg.edu.nus.iss.pizzaappredo.model.Order;
import sg.edu.nus.iss.pizzaappredo.model.Pizza;
import sg.edu.nus.iss.pizzaappredo.repository.PizzaRepository;

@Service
public class PizzaService {
   @Autowired
   private PizzaRepository pizzaRepo;

   public static final String[] PIZZA_NAMES = {
    "bella",  
    "margherita", 
    "marinara", 
    "spianatacalabrese", 
    "trioformaggio"
};

    public static final String[] PIZZA_SIZES = {
        "sm",
        "md",
        "lg",
    };

    public final Set<String> pizzaNames;
    public final Set<String> pizzaSizes;

    public PizzaService() {
        pizzaNames = new HashSet<String>(Arrays.asList(PIZZA_NAMES));
        pizzaSizes = new HashSet<String>(Arrays.asList(PIZZA_SIZES));
    }

    public Optional<Order> getOrderID(String orderId) {
        return pizzaRepo.findByOrderId(orderId);
    }

    public double calculateCost(Order order) {
        double total = 0;
        switch(order.getPizzaName()) {
            case "margherita":
            total+=22;
            break;
            
            case "trioformaggio":
            total+=25;
            break;

            case "bella", "", "marinara", "spianatacalabrese":
            total+=30;
            break;

        }

        switch(order.getSize()) {
            case "sm":
            total*=1;
            break;

            case "md":
            total*=1.2;
            break;

            case "lg":
            total*=1.5;
            break;

            default:
        }

        total *= order.getQuantity();
        if(order.getRush()) {
            total += 2;
        }

        order.setTotalCost(total);
        return total;
    }



    public Order saveOrder(Pizza p, Delivery d) {
        Order order = new Order(p,d);
        calculateCost(order);
        pizzaRepo.save(order);
        return order;

    }

    public List<ObjectError> validatePizzaOrder(Pizza p) {
        List<ObjectError> errors = new LinkedList<>();
        FieldError error;

        if(!pizzaNames.contains(p.getPizza().toLowerCase())) {
            error = new FieldError("pizza", "pizza", "Invalid pizza name");
            errors.add(error);
        }

        if(!pizzaSizes.contains(p.getSize().toLowerCase())) {
            error = new FieldError("pizza", "size", "Invalid pizza size");
            errors.add(error);
        }

        return errors;
    }
    



    }


