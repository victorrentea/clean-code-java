package victor.training.trivia;

import java.util.ArrayList;
import java.util.List;

public class GameBetter implements IGame {
   public static final int BOARD_SIZE = 12;
   private final QuestionRepository questionRepository = new QuestionRepository();

   private List<Player> players = new ArrayList<>();
   private int[] places = new int[6];
   private int[] purses = new int[6];
   private boolean[] inPenaltyBox = new boolean[6];

   int currentPlayer = 0;

   public void addPlayer(String playerName) {
      players.add(new Player(playerName));
      // TODO e un bug ascuns in cod. Gaseste-l

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
   }

   public void roll(int roll) {
      System.out.println(currentPlayer().getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer().isInPenaltyBox()) {
         if (roll % 2 == 0) {
            System.out.println(currentPlayer().getName() + " is not getting out of the penalty box");
            return;
         }
         currentPlayer().free();
         System.out.println(currentPlayer().getName() + " is getting out of the penalty box");
      }
      currentPlayer().advance(roll);
      System.out.println(currentPlayer().getName() + "'s new location is " + currentPlayer().getPlace());
      System.out.println("The category is " + questionRepository.getCurrentCategory(currentPlayer().getPlace()).getLabel());
      askQuestion();
   }

   private Player currentPlayer() {
      return players.get(currentPlayer);
   }

   private void askQuestion() {
      System.out.println(questionRepository.nextQuestion(currentPlayer().getPlace()));
   }

   public void correctAnswer() {
      if (isGameOver()) {
         return;
      }

      if (!currentPlayer().isInPenaltyBox()) {
         System.out.println("Answer was correct!!!!");
         currentPlayer().reward();
         System.out.println(currentPlayer().getName() + " now has " + currentPlayer().getPurse() + " Gold Coins.");

         if (isGameOver()) {
            return;
         }
      }
      nextPlayer();
   }

   @Override
   public boolean isGameOver() {
      return currentPlayer().isWinner();
   }


   public void wrongAnswer() {
      if (isGameOver()) {
         return;
      }

      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getName() + " was sent to the penalty box");
      currentPlayer().punish();

      nextPlayer();
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == players.size()) {
         currentPlayer = 0;
      }
   }
}
