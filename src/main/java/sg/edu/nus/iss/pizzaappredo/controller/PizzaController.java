package sg.edu.nus.iss.pizzaappredo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzaappredo.model.Delivery;
import sg.edu.nus.iss.pizzaappredo.model.Order;
import sg.edu.nus.iss.pizzaappredo.model.Pizza;
import sg.edu.nus.iss.pizzaappredo.service.PizzaService;

@Controller
@RequestMapping
public class PizzaController {
@Autowired 
private PizzaService pizzaService;
    @GetMapping(path="/")
    public String index(Model model, HttpSession session) {
        session.invalidate();
        model.addAttribute("pizza", new Pizza());
      
        return "index";
        
    }

    @PostMapping(path = "/pizza", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPizza(Model model, HttpSession session, @Valid Pizza pizza, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }

        List<ObjectError> errors = pizzaService.validatePizzaOrder(pizza);
        if(!errors.isEmpty()) {
            for(ObjectError e : errors) 
            result.addError(e);
            return "index";
            
        }

        session.setAttribute("pizza", pizza);
        model.addAttribute("delivery", new Delivery());
        return "delivery";
    }

    @PostMapping(path = "/pizza/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postOrder(Model model, HttpSession session, @Valid Delivery delivery, BindingResult result) {
        if (result.hasErrors()) {
            return "delivery";
        }
        
        Pizza p = (Pizza) session.getAttribute("pizza");
        Order o = pizzaService.saveOrder(p, delivery);
        model.addAttribute("order", o);
        return "order";
    }
    
}
