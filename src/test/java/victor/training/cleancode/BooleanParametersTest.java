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
   void bossLevelFluff(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5)), false);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic4: Validate Task{id=5, running=false}\n" +
                                     "Logic5 index=1 on running=true\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic7");
   }
   @Test
   @CaptureSystemOutput
   void bossLevelFluff_c323(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5)), true);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic4: Validate Task{id=5, running=false}\n" +
                                     "My Logic: Task{id=5, running=true}\n" +
                                     "Logic5 index=1 on running=true\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic7");
   }

   @Test
   @CaptureSystemOutput
   void bossLevelFluff_emptyList(OutputCapture outputCapture) {
      target.bossLevel(true, Collections.emptyList(), false);

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
      target.bossLevel(false, List.of(new Task(5)), false);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                              "Logic2\n" +
                              "Logic7 [Task{id=5, running=false}]\n" +
                              "Logic7\n");
   }

   @Test
   @CaptureSystemOutput
   void stuffForEachElement_inWateverOrder(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5),new Task(6)), false);

      assertThat(outputCapture.toString())
          .contains("Logic4: Validate Task{id=5, running=false}")
          .contains("Logic4: Validate Task{id=6, running=false}")
          .contains("Logic5 index=1 on running=true")
          .contains("Logic5 index=2 on running=true")
      ;
   }
}