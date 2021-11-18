package victor.training.exception;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExBasics {
   public static void main(String[] args) {
      ook();

   }

   private static void ook() {
      try {
         c();
      } catch (ParseException e) {
//         log.erorr("BOO", e);
         throw new RuntimeException("Bad happended " + e.getMessage(), e);
      }
   }

   private static void c() throws ParseException {
      b();
   }

   private static void b() {
      a();
   }

   private static void a() {
      method();
   }

//   @SneakyThrows
   static public void method()  {

      try {
         new SimpleDateFormat().parse("a");
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }
}
