package victor.training.cleancode;

import lombok.SneakyThrows;
import org.jooq.lambda.Unchecked;

import java.util.stream.Stream;

public class HidingCheckedExceptions {

   public static void main(String[] args) {
      Stream.of("a","a","a","a","a","a","a")
          .map(Unchecked.function(UtilsEx::methodx)) // solutia a doua jool JAR
//          .map(UtilsEx::methodx)
          .forEach(System.out::println);
   }

}



class UtilsEx {
//   @SneakyThrows -- daca ai acces
   public static String methodx(String s) throws Exception {
       return s.toUpperCase();
   }
}
