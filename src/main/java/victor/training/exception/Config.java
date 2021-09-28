package victor.training.exception;

import lombok.SneakyThrows;
import victor.training.exception.MyException.ErrorCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Alan T.
 */
public class Config {

   @SneakyThrows
   public static Date getLastPromoDate() { // java e sg lmbaj din lume care are exceptii checked
      try {
      File file = new File("config.properties");
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Properties properties = new Properties();
      try (FileReader reader = new FileReader(file)) {
         properties.load(reader);
      }
      return format.parse(properties.getProperty("last.promo.date"));
      } catch (IOException | ParseException e) {
         throw new RuntimeException("Bad Config", e); //
//         throw new MyException(ErrorCode.BAD_CONFIG);
      }
   }


//   @Retryable
   public void method() {
//      RestTemplate//
      // feign client
   }

}
