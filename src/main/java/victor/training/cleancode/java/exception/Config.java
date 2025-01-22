package victor.training.cleancode.java.exception;


import java.util.Date;


//@Component
public class Config {

   public Date getLastPromoDate() {
//      File file = new File("config.properties");
//      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//      Properties properties = new Properties();
//      try (FileReader reader = new FileReader(file)) {
//         properties.load(reader);
//      }
//      return format.parse(properties.getProperty("last.promo.date"));
      return new Date();
   }


}
