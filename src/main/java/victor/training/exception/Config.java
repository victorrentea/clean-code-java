package victor.training.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

   private static final Logger log = LoggerFactory.getLogger(Config.class);

   public static Date getLastPromoDate() {
         File file = new File("config.properties");
      try {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Properties properties = new Properties();
         try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
         }
         return format.parse(properties.getProperty("last.promo.date"));
//      return new Date();
      } catch (IOException | ParseException e) {
//         log.error(e);
         throw new MyException(ErrorCode.BAD_CONFIG, "Expected file at " + file.getAbsolutePath(), e);
      }
   }


}
