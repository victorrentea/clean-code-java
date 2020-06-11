package victor.training.trivia;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameRefactored implements IGame {
   private final Writer writer;

   List<Player> players = new ArrayList<>();
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   private List<String> popQuestions = new LinkedList<>();
   private List<String> scienceQuestions = new LinkedList<>();
   private List<String> sportsQuestions = new LinkedList<>();
   private List<String> rockQuestions = new LinkedList<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

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

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   @Override
   public boolean add(String playerName) {
      players.add(new Player(playerName));
      purses[howManyPlayers()] = 0;
      inPenaltyBox[howManyPlayers()] = false;

      writeText(playerName + " was added");
      writeText("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   @Override
   public void roll(int roll) {
      writeText(currentPlayer().getName() + " is the current player");
      writeText("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
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
      return players.get(currentPlayer);
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
      if (inPenaltyBox[currentPlayer]) {
         return penaltyBoxCorrectAnswers();
      } else {
         return defaultCorrectAnswers();
      }
   }

   private boolean penaltyBoxCorrectAnswers() {
      if (isGettingOutOfPenaltyBox) {
         return defaultCorrectAnswers();
      } else {
         currentPlayer++;
         if (currentPlayer == howManyPlayers()) currentPlayer = 0;
         return true;
      }
   }

   private boolean defaultCorrectAnswers() {
      writeText("Answer was correct!!!!");
      purses[currentPlayer]++;
      writeText(currentPlayer().getName() + " now has "
          + purses[currentPlayer]
          + " Gold Coins.");

      boolean winner = didPlayerWin();
      currentPlayer++;
      if (currentPlayer == howManyPlayers()) currentPlayer = 0;

      return winner;
   }

   @Override
   public boolean wrongAnswer() {
      writeText("Question was incorrectly answered");
      writeText(currentPlayer().getName() + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == howManyPlayers()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
