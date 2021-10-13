package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import victor.training.cleancode.Employee;
import victor.training.cleancode.SplitLoop;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SplitLoopTest {
   @Test
   public void characterizationTest() {
      String actual = new SplitLoop().computeStats(asList(
          new Employee(1, 24, 2000, false),
          new Employee(1, 27, 2000, false),
          new Employee(2, 28, 1500, true),
          new Employee(3, 30, 2500, true)));
      assertEquals("Average age = 25; Average salary = 2000.0", actual);
   }
}
