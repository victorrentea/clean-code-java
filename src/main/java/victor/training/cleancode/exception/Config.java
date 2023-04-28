package victor.training.cleancode.exception;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


@Component
public class Config {


//   @SneakyThrows
   // Java is the only lang in the world that did this mistake: checked exceptions.
   public Date getLastPromoDate()  {
      try {
         File file = new File("config.properties");
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Properties properties = new Properties();
         try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
         }
         return format.parse(properties.getProperty("last.promo.date"));
         //      return new Date();
      } catch (IOException | ParseException e) {
         throw new RuntimeException(e); // ALWAYS DO THIS
      }
   }

   //

}
