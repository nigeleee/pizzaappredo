package sg.edu.nus.iss.pizzaappredo.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pizzaappredo.model.Order;

@Repository
public class PizzaRepository {
    @Autowired @Qualifier ("pizza")

    private RedisTemplate<String, String> template;

    //call redis template to save Order object as json string in redis database
    public void save(Order order) {
        template.opsForValue().set(order.getOrderId(), order.toJSON().toString());
    }
//retrieve Order object from redis database by orderId
    public Optional<Order> findByOrderId(String orderId) {
        String json = template.opsForValue().get(orderId);
        return Optional.ofNullable(json)
        .filter(str -> str.trim().isEmpty())
        .map(Order :: create);   
    }
}
