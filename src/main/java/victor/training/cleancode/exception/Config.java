package victor.training.cleancode.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

   // cand eu zic throws, callerul meu afla ca eu lucrez cu Fisier si parsez date.=
   // afla din implem mea = Abstraction Leak
   public Date getLastPromoDate()  {
      File file = new File("config.properties");
      Properties properties = new Properties();
      try {
         try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
         }
         String forObject = new RestTemplate().getForObject("aa", String.class);
//      Thread.sleep(1000);
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         return format.parse(properties.getProperty("last.promo.date"));
      } catch (IOException | ParseException e) {
         throw new RuntimeException(e);
//         throw new ExceptiaMeaRuntime(e);
      }
   }


}
