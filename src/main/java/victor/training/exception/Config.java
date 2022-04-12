package victor.training.exception;

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

   public static Date getLastPromoDate() throws IOException, ParseException {
      File file = new File("config.properties");
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//      String dinBaza = "2022-0131";
      Properties properties = new Properties();
      try (FileReader reader = new FileReader(file)) {
         properties.load(reader);
      }
      String dinBaza = properties.getProperty("last.promo.date");
      return format.parse(dinBaza);
//      return new Date();
   }

   // Maria: le ridici pana intr-un handler de exceptii care le gestioneaza cumva uniform
   // ! CE FACE acel handler ? > se uita la tipul exceptiei si da mesaje customizate userilor / notificari.


}
