package sg.edu.nus.iss.pizzaappredo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.pizzaappredo.model.Order;
import sg.edu.nus.iss.pizzaappredo.service.PizzaService;

@RestController
@RequestMapping(path="/order", produces=MediaType.APPLICATION_JSON_VALUE)
public class PizzaRestController {
   @Autowired
   private PizzaService pizzaService;
   
   @GetMapping(path="{orderId}")
   public ResponseEntity<String> getOrderDetails(@PathVariable String orderId) {
        Optional<Order> order = pizzaService.getOrderID(orderId);
        if(order.isEmpty()) {
            JsonObject error = Json.createObjectBuilder().add("message", "Order %s not found".formatted(orderId)).build();
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }
    
        return ResponseEntity.ok(order.get().toJSON().toString());
       
   }

   
}
