package victor.training.samples.one;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class U4RDateUtil {
   public static Date getStringAsDate(String firstAugust2016, String iso8601DateFormat) {
      try {
         return new SimpleDateFormat("asdadda").parse(firstAugust2016);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }
}
