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
      target.bossLevel(List.of(new Task(5), new Task(6)));

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic4: Validate Task(id=5, running=false)\n" +
                                     "Logic4: Validate Task(id=6, running=false)\n" +
                                     "Logic5 1 on true\n" +
                                     "Logic5 2 on true\n" +
                                     "Logic6 2\n" +
                                     "Task Ids: [5, 6]\n" +
                                     "Logic7\n");
   }

   @Test
   @CaptureSystemOutput
   void bossLevelEmptyList(OutputCapture outputCapture) {
      target.bossLevel(Collections.emptyList());

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
      target.bossLevelNoFluff(List.of(new Task(5)));

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                              "Logic2\n" +
                              "Logic7 [Task(id=5, running=false)]\n" +
                              "Logic7\n");
   }
}