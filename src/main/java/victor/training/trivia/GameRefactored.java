package victor.training.trivia;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class GameRefactored implements IGame {
   // TODO: maybe move all string formatting to a different class.
   private final Writer writer;
   private final List<Player> players = new ArrayList<>();
   private final QuestionSet questions = new QuestionSet();

   private int currentPlayerIndex = 0;
   private boolean isGettingOutOfPenaltyBox;

   public GameRefactored(Writer writer) {
      this.writer = writer;
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
      writeText("The category is " + Category.getCategoryForPlace(currentPlayer().getPlace()).label);
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

   private void askQuestion() {
      Category category = Category.getCategoryForPlace(currentPlayer().getPlace());
      String question = questions.getNextQuestionFor(category);
      writeText(question);
   }

   @Override
   public boolean correctAnswer() {
      if (currentPlayer().isInPenaltyBox()) {
         return penaltyBoxCorrectAnswers();
      } else {
         return defaultCorrectAnswers();
      }
   }

   private boolean penaltyBoxCorrectAnswers() {
      if (isGettingOutOfPenaltyBox) {
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
      boolean winner = currentPlayer().isWinner();
      endTurn();
      return !winner;
   }

   private Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }


   private void endTurn() {
      currentPlayerIndex++;
      if (currentPlayerIndex == players.size()) {
         currentPlayerIndex = 0;
      }
   }

   @Override
   public boolean wrongAnswer() {
      writeText("Question was incorrectly answered");
      writeText(currentPlayer().getName() + " was sent to the penalty box");
      currentPlayer().moveInPenaltyBox();
      endTurn();
      return true;
   }
}
