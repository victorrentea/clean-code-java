package victor.training.experiment;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.function.Function;

@RestController
@SpringBootApplication
public class JacksonPlay {
   public static void main(String[] args) {
       SpringApplication.run(JacksonPlay.class, args);
   }
   
   @GetMapping
   public void get(@RequestParam MyId id) {
      System.out.println(id);
   }
}

@Data
class MyDto {
//   MyId id;
}
@Data
// TODO convince Swagger to put a string in the header.
class MyId {
   private String id;
   public MyId(String id) { this.id = id;}
}
@Component
class StringToLocalDateTimeConverter implements Converter<String, MyId> {
   @Override
   public MyId convert(String source) {
      return new MyId(source);
   }
}

// you only need Function<> if you play with Stream/Optional.map()
//@FunctionalInterface
//interface StringToUUID{
//   UUID convert(String str) ;
//}
//class StringToUUIDImpl implements StringToUUID {
//   @Override
//   public UUID apply(String s) {
//      return null;
//   }
//}


class YourCode {

   public void method() {

   }
}