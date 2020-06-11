package victor.training.trivia;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.function.Function;

public class GamePlay {
   public static void main(String[] args) {

      int seed = 1;
      Function<StringWriter, Game> constructor = Game::new;


      String output = runGame(constructor, seed);

      System.out.println(output);
   }

   private static String runGame(Function<StringWriter, Game> constructor, int seed) {
      Random random = new Random(seed);
      StringWriter sw = new StringWriter();
      Game aGame = constructor.apply(sw);
      aGame.add("Chet");
      aGame.add("Pat");
      aGame.add("Sue");

      boolean gameContinues = true;
      while (gameContinues) {
         aGame.roll(random.nextInt(6) + 1);

         if (random.nextInt(9) == 7) {
            // 1/9: 11% sansa sa raspunda gresit
            gameContinues = aGame.wrongAnswer();
         } else {
            gameContinues = aGame.wasCorrectlyAnswered();
         }

      }
      return sw.toString();
   }
}
