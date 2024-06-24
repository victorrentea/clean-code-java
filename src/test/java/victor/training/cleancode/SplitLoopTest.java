package victor.training.cleancode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
