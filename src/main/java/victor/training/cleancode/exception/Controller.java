package victor.training.cleancode.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;
import victor.training.cleancode.exception.model.Order;

import java.util.Date;

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


//      try {
         biz.applyDiscount(new Order(new Date()), null);
//      } catch (Exception e) {
//         return ServerResponse.status(302).location("")
//      }
   }
}
