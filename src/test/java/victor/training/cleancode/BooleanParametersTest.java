package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import victor.training.testing.tools.CaptureSystemOutput;
import victor.training.testing.tools.CaptureSystemOutput.OutputCapture;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

class BooleanParametersTest {

   private final BooleanParameters target = new BooleanParameters();

   @Test
   @CaptureSystemOutput
   void bossLevel(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5)), true);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic4: Validate Task(id=5, running=false)\n" +
                                     "Logic5 1 on true\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic7");
   }

   @Test
   @CaptureSystemOutput
   void bossLevelEmptyList(OutputCapture outputCapture) {
      target.bossLevel(true, Collections.emptyList(), true);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic6 0\n" +
                                     "Task Ids: []\n" +
                                     "Logic7\n");
   }
   @Test
   @CaptureSystemOutput
   void bossLevelFalse(OutputCapture outputCapture) {
      target.bossLevel(false, List.of(new Task(5)), true);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                              "Logic2\n" +
                              "Logic7 [Task(id=5, running=false)]\n" +
                              "Logic7\n");
   }
}