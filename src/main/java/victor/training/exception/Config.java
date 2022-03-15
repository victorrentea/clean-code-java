package victor.training.exception;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class Config {

   @SneakyThrows
   public static Date getLastPromoDate() {
//      try {
         File file = new File("config.properties");
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Properties properties = new Properties();
         try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
         }
         return format.parse(properties.getProperty("last.promo.date"));
//      } catch (ParseException | IOException e) {
////         log.error(e.getMessage(), e); // DO THIS if you want to ignore the error and contniue
////         return null;
//         throw new RuntimeException(e); // DO THIS 90% when faced with a checke
//      }
   }


}
