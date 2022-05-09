package victor.training.cleancode.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;


@Slf4j
@Component
public class Config {

   public Date getLastPromoDate() throws EndDateNotAvailableException {
         File file = new File("config.propertiess");
      try {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         Properties properties = new Properties();
         try (FileReader reader = new FileReader(file)) {
            properties.load(reader);
         }
         return format.parse(properties.getProperty("last.promo.date"));
//      return new Date();
      } catch (ParseException | IOException e) {
         throw new RuntimeException("Nu am gasit fisdierul: " + file.getAbsolutePath() ,e);
         // fatal exception si o prinzi  intr-un generic handler

//         log.error("nu-i" + e);
//         return Optional.empty(); // ciudat, ca pana la urma ascunzi o eroare.

//         throw new EndDateNotAvailableException(e); // daca anticipezi ca pe undeva pe parcurs, cineva va catch (ExceptiaAsta) < ERORI RECUPERABILE
      }
   }
}


