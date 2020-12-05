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
   boolean isGettingOutOfPenaltyBox;

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
            isGettingOutOfPenaltyBox = false;
            return;
         }
         isGettingOutOfPenaltyBox = true;
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

// TODO e un nume misleading care e ?
   public boolean wasCorrectlyAnswered() {
      if (currentPlayer().isInPenaltyBox()) {
         if (isGettingOutOfPenaltyBox) {
            return correctAnswer();
         } else {
            nextPlayer();
            return true;
         }
      } else {
         return correctAnswer();
      }
   }

   private boolean correctAnswer() {
      System.out.println("Answer was correct!!!!");
      currentPlayer().reward();
      System.out.println(currentPlayer().getName() + " now has " + currentPlayer().getPurse() + " Gold Coins.");

      boolean isGameOver = !didPlayerWin();
      nextPlayer();

      return isGameOver;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getName() + " was sent to the penalty box");
      currentPlayer().punish();

      nextPlayer();
      return true;
   }

   private void nextPlayer() {
      currentPlayer++;
      if (currentPlayer == players.size()) {
         currentPlayer = 0;
      }
   }

   private boolean didPlayerWin() {
      return currentPlayer().getPurse() == 6;
   }
}
