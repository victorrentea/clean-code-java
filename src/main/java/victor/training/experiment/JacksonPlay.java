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