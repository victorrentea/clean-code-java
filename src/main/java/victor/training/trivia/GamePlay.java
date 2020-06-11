package victor.training.trivia;

import java.util.Random;

public class GamePlay {
   public static void main(String[] args) {
      Game aGame = new Game();
      aGame.add("Chet");
      aGame.add("Pat");
      aGame.add("Sue");

      boolean notAWinner = false;
      do {
         aGame.roll(new Random().nextInt(5) + 1);

         if (new Random().nextInt(9) == 7) {
            notAWinner = aGame.wrongAnswer();
         } else {
            notAWinner = aGame.wasCorrectlyAnswered();
         }

      } while (notAWinner);
   }
}
