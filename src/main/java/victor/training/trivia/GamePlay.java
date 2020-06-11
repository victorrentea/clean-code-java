package victor.training.trivia;

import org.junit.Test;

import java.io.StringWriter;
import java.util.Random;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class GamePlay {
   @Test
   public void characterizationTest() {
      for (int seed = 1; seed < 10_000; seed ++) {
         String expected = runGame(Game::new, seed);
         String actual = runGame(GameRefactored::new, seed);

         assertEquals(expected, actual);
      }
   }

   private static String runGame(Function<StringWriter, IGame> constructor, int seed) {
      Random random = new Random(seed);
      StringWriter sw = new StringWriter();
      IGame aGame = constructor.apply(sw);
      aGame.add("Chet");
      aGame.add("Pat");
      aGame.add("Sue");

      boolean notWinner = true;
      while (notWinner) {
         aGame.roll(random.nextInt(6) + 1);

         if (random.nextInt(9) == 7) {
            // 1/9: 11% sansa sa raspunda gresit
            notWinner = aGame.wrongAnswer();
         } else {
            notWinner = aGame.correctAnswer();
         }

      }
      return sw.toString();
   }
}
