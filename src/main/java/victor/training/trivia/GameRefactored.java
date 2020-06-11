package victor.training.trivia;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameRefactored implements IGame {
   private final Writer writer;

   private final List<Player> players = new ArrayList<>();

   private List<String> popQuestions = new LinkedList<>();
   private List<String> scienceQuestions = new LinkedList<>();
   private List<String> sportsQuestions = new LinkedList<>();
   private List<String> rockQuestions = new LinkedList<>();

   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;

   public GameRefactored(Writer writer) {
      this.writer = writer;
      for (int i = 0; i < 50; i++) {
         popQuestions.add("Pop Question " + i);
         scienceQuestions.add("Science Question " + i);
         sportsQuestions.add("Sports Question " + i);
         rockQuestions.add("Rock Question " + i);
      }
   }

   private void writeText(Object text) {
      try {
         writer.write(text + "\n");
         writer.flush();
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public boolean add(String playerName) {
      players.add(new Player(playerName));
      writeText(playerName + " was added");
      writeText("They are player number " + players.size());
      return true;
   }

   @Override
   public void roll(int roll) {
      writeText(currentPlayer().getName() + " is the current player");
      writeText("They have rolled a " + roll);
      if (currentPlayer().isInPenaltyBox()) {
         penaltyBoxMove(roll);
      } else {
         defaultMove(roll);
      }
   }

   private void defaultMove(int roll) {
      currentPlayer().move(roll);

      writeText(currentPlayer().getName() + "'s new location is " + currentPlayer().getPlace());
      writeText("The category is " + currentCategory().label);
      askQuestion();
   }

   private void penaltyBoxMove(int roll) {
      if (roll % 2 != 0) {
         isGettingOutOfPenaltyBox = true;
         writeText(currentPlayer().getName() + " is getting out of the penalty box");
         defaultMove(roll);
      } else {
         writeText(currentPlayer().getName() + " is not getting out of the penalty box");
         isGettingOutOfPenaltyBox = false;
      }
   }

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

   private void askQuestion() {
      writeText(extractNextQuestion());
   }

   private String extractNextQuestion() {
      switch (currentCategory()) {
         case POP: // Map<Category,List<String>>
            return popQuestions.remove(0);
         case SCIENCE:
            return scienceQuestions.remove(0);
         case SPORTS:
            return sportsQuestions.remove(0);
         case ROCK:
            return rockQuestions.remove(0);
         default:
            throw new IllegalStateException("Unexpected value: " + currentCategory());
      }
   }


   private Category currentCategory() {
      // 0 .. 11 pozitii: putem scurta asta : ?
      switch (currentPlayer().getPlace() % 4) {
         case 0:
            return Category.POP;
         case 1:
            return Category.SCIENCE;
         case 2:
            return Category.SPORTS;
         case 3:
            return Category.ROCK;

         default:
            throw new IllegalStateException("Unexpected value: " + currentPlayer().getPlace() % 4);
      }
   }

   @Override
   public boolean wasCorrectlyAnswered() {
      if (currentPlayer().isInPenaltyBox()) {
         return penaltyBoxCorrectAnswers();
      } else {
         return defaultCorrectAnswers();
      }
   }

   private boolean penaltyBoxCorrectAnswers() {
      if (isGettingOutOfPenaltyBox) {
         // AICI LIPSESTE CEVA
         currentPlayer().moveOutOfPenaltyBox();
         return defaultCorrectAnswers();
      } else {
         endTurn();
         return true;
      }
   }

   private boolean defaultCorrectAnswers() {
      writeText("Answer was correct!!!!");
      currentPlayer().addCoin();
      writeText(currentPlayer().getName() + " now has " + currentPlayer().getPurse() + " Gold Coins.");
      boolean winner = currentPlayer().getPurse() != 6;
      endTurn();
      return winner;
   }

   @Override
   public boolean wrongAnswer() {
      writeText("Question was incorrectly answered");
      writeText(currentPlayer().getName() + " was sent to the penalty box");
      currentPlayer().moveInPenaltyBox();
      endTurn();
      return true;
   }


   private void endTurn() {
      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) {
         currentPlayerIndex = 0;
      }
   }
}
