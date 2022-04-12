package victor.training.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import victor.training.exception.model.Order;

@RestController
@SpringBootApplication
public class Controller {
   public static void main(String[] args) {
       SpringApplication.run(Controller.class, args);
   }

   @Autowired
   private Biz biz;

   @GetMapping
   public void webEndpoint() {
//      biz.applyDiscount(new Order());
   }
}
