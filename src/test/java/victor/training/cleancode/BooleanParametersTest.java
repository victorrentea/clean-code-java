package victor.training.cleancode;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import victor.training.testing.tools.CaptureSystemOutput;
import victor.training.testing.tools.CaptureSystemOutput.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

class BooleanParametersTest {

   private final BooleanParameters target = new BooleanParameters();

   @Test
   @CaptureSystemOutput
   void bossLevelFluff(OutputCapture outputCapture) {
      target.bossLevelFluff(ImmutableList.of(new Task(5)));

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic4: Validate Task{id=5, started=false}\n" +
                                     "Audit task index=1: Task{id=5, started=true}\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic7\n");
   }
   @Test
   @CaptureSystemOutput
   void bossLevelFluff_c323(OutputCapture outputCapture) {
      target.bossLevelFluff323(ImmutableList.of(new Task(5)));

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic3\n" +
                                     "Logic4: Validate Task{id=5, started=false}\n" +
                                     "My Logic: Task{id=5, started=true}\n" +
                                     "Audit task index=1: Task{id=5, started=true}\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic7");
   }

   @Test
   @CaptureSystemOutput
   void bossLevelFluff_emptyList(OutputCapture outputCapture) {
      target.bossLevelFluff(ImmutableList.of());

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
      target.bossLevelNoFluff(ImmutableList.of(new Task(5)));

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic2\n" +
                                     "Logic7 [Task{id=5, started=false}]\n" +
                                     "Logic7\n");
   }

   @Test
   @CaptureSystemOutput
   void stuffForEachElement_inWateverOrder(OutputCapture outputCapture) {
      target.bossLevelFluff(ImmutableList.of(new Task(5),new Task(6)));

      // we don't care in what order we validate or audit tasks
      assertThat(outputCapture.toString())
          .contains("Logic4: Validate Task{id=5, started=false}")
          .contains("Logic4: Validate Task{id=6, started=false}")
          .contains("Audit task index=1: Task{id=5, started=true}")
          .contains("Audit task index=2: Task{id=6, started=true")
      ;
   }
}