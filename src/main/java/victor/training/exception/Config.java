package victor.training.exception;

import victor.training.exception.MyException.ErrorCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
//class BadCOnfigException extends RuntimeException {}
/**
 * @author Alan T.
 */
public class Config {

   public static Date getLastPromoDate()  {
//      LocalDateTime d = LocalDateTime.parse("2022-02-02");
      try {
         File file = new File("config.properties");
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//      String dinBaza = "2022-0131";
         Properties properties = new Properties();
         try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
         }
         String dinBaza = properties.getProperty("last.promo.date");
         return format.parse(dinBaza);
      }catch (IOException | ParseException e) {
         throw new RuntimeException("Bad Config", e);
         //.onErrorContinue pt Fluxuri infinite in Reactor
//         throw new MyException(ErrorCode.BAD_CONFIG, e); // cand prezinti erorile cumva frumos userului
      }
//      return new Date();
   }

   // Maria: le ridici pana intr-un handler de exceptii care le gestioneaza cumva uniform
   // ! CE FACE acel handler ? > se uita la tipul exceptiei si da mesaje customizate userilor / notificari.


}
