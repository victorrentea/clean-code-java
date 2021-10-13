package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.Employee;
import victor.training.cleancode.SplitLoop;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitLoopTest {
   @Test
   public void test() {
      String actual = new SplitLoop().computeStats(asList(
          new Employee(1,24, 2000, false),
          new Employee(1,30, 2000, false),
          new Employee(2,28, 1500, true),
          new Employee(3,30, 2500, true)));
      assertEquals("avg age = 27; avg sal = 2000.0", actual);
   }
}
