package victor.training.cleancode.java.extra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static victor.training.cleancode.java.refactoring.SplitVariable.discount;

public class SplitVariableTest {
   @Test
   public void test() {
      assertEquals(1, discount(1,1));
      assertEquals(50, discount(50,1));
      assertEquals(49, discount(51,1));
      assertEquals(0, discount(1,101));
      assertEquals(49, discount(50,101));
      assertEquals(48, discount(51,101));
   }
}
