package victor.training.cleancode;

import org.jooq.lambda.Unchecked;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class CheckedInStreams {

   public static void main(String[] args) {
      List<String> list = asList("2021-02-01", "2021-0f-02");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


      List<Date> collect = list.stream().map(Unchecked.function(sdf::parse)).collect(Collectors.toList());
   }

//   private static Function<String, Date> convert(CheckedFunction<String, Date> f) {
//      return s -> {
//         try {
//            return f.apply(s);
//         } catch (Exception e) {
//            throw new RuntimeException(e);
//         }
//      };
//   }
}


 interface CheckedFunction<T, R> {

   R apply(T t) throws Exception;
}