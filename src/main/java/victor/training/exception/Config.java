package victor.training.exception;

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

   public static Date getLastPromoDate() {
      File file = new File("config.properties");
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      Properties properties = new Properties();
      try (FileReader reader = new FileReader(file)) {
         properties.load(reader);
         return format.parse(properties.getProperty("last.promo.date"));
      } catch (IOException | ParseException e) {
//         throw new ConfigPromoDateInvalidException(e); NU ASA decat daca ii fac catch doar pe ea in alt layer
         throw new MyException(ErrorCode.BAD_CONFIG, e , "promotion date");
      }
//      return new Date();
   }

   // http://www.gwtproject.org/javadoc/latest/com/google/gwt/core/client/GWT.UncaughtExceptionHandler.html

   public void genericHandle(Throwable e) {

   }


}
