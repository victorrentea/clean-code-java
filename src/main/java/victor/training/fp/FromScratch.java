package victor.training.fp;

import org.jooq.lambda.Unchecked;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

public class FromScratch {


   public static void main(String[] args) throws IOException {

      Writer writer = new FileWriter("out.txt");

      List.of("a","b","c", "C","D")
          .stream()
          .filter(s -> s.equals(s.toUpperCase()))
          .forEach(Unchecked.consumer(writer::write));
//          .forEach(wrapCheckedExceptions(writer::write));
   }

   private  static <T> Consumer<T> wrapCheckedExceptions(Consumer2<T> iHave) {
      return x -> {
         try {
            iHave.accept(x);
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      };
   }
}

@FunctionalInterface
interface Consumer2<T> {
   void accept(T t) throws Exception;
}