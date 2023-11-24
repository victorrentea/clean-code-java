package victor.training.cleancode;

import org.junit.jupiter.api.Test;
import victor.training.testing.tools.CaptureSystemOutput;
import victor.training.testing.tools.CaptureSystemOutput.OutputCapture;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

class BossTest {

   private final Boss target = new Boss();

   @Test
   @CaptureSystemOutput
   void bossLevelFluff(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5)), false);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic3\n" +
                                     "Starting Task(id=5, started=false)\n" +
                                     "Audit task #1: Task(id=5, started=true)\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic8\n");
   }
   @Test
   @CaptureSystemOutput
   void bossLevelFluff_c323(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5)), true);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic3\n" +
                                     "Starting Task(id=5, started=false)\n" +
                                     "My Logic: Task(id=5, started=true)\n" +
                                     "Audit task #1: Task(id=5, started=true)\n" +
                                     "Logic6 1\n" +
                                     "Task Ids: [5]\n" +
                                     "Logic8");
   }

   @Test
   @CaptureSystemOutput
   void bossLevelFluff_emptyList(OutputCapture outputCapture) {
      target.bossLevel(true, Collections.emptyList(), false);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic3\n" +
                                     "Logic6 0\n" +
                                     "Task Ids: []\n" +
                                     "Logic8\n");
   }
   @Test
   @CaptureSystemOutput
   void bossLevelFalse(OutputCapture outputCapture) {
      target.bossLevel(false, List.of(new Task(5)), false);

      assertThat(outputCapture.toString())
          .isEqualToIgnoringNewLines("Logic1\n" +
                                     "Logic7 on fluff=false [Task(id=5, started=false)]\n" +
                                     "Logic8\n");
   }

   @Test
   @CaptureSystemOutput
   void stuffForEachElement_inWateverOrder(OutputCapture outputCapture) {
      target.bossLevel(true, List.of(new Task(5),new Task(6)), false);

      // we don't care in what order we validate or audit tasks
      assertThat(outputCapture.toString())
              .contains("Starting Task(id=5, started=false)")
              .contains("Starting Task(id=6, started=false)")
          .contains("Audit task #1: Task(id=5, started=true)")
          .contains("Audit task #2: Task(id=6, started=true)")
      ;
   }
}