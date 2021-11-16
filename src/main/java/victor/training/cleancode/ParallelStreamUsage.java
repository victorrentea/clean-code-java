package victor.training.cleancode;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.System.currentTimeMillis;

@Slf4j
public class ParallelStreamUsage {


   public static void main(String[] args) {
      long t0 = currentTimeMillis();
      List<Integer> numbers = IntStream.range(1,1_00).boxed().collect(Collectors.toList());
      numbers.parallelStream().map(e -> heavyCpu(e)).collect(Collectors.toList());
      long t1 = currentTimeMillis();
      System.out.println(t1-t0);
   }

   private static String heavyCpu(Integer e) {
      log.info("On what thread am I ");
//      REST call for 1sec
      return "" + e;
   }
}
