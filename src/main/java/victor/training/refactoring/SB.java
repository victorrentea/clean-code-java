package victor.training.refactoring;

import lombok.extern.slf4j.Slf4j;
import org.jooq.lambda.Unchecked;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SB {
   public static void main(String[] args) {

      String r = args[0] + " a " + args[2] + "aaa";


//      for (String arg : args) {
//         r += arg;
//      }
      r += Stream.of(args).collect(Collectors.joining());


//      if (log.isDebugEnabled()) {
         log.debug("a {} sada", args) ;
//      }

   }
}
