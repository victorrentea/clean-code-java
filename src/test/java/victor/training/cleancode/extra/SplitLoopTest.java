package victor.training.cleancode.extra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import victor.training.cleancode.extra.Employee;
import victor.training.cleancode.extra.SplitLoop;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitLoopTest {
   @Test
   public void test() {
      String actual = SplitLoop.computeStats(asList(
          new Employee(24, 2000, false),
          new Employee(28, 1500, true),
          new Employee(30, 2500, true)));
      assertEquals("avg age = 24; avg sal = 2000.0", actual);
   }
}
