package victor.training.trivia;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameRefactored implements IGame {
   private final Writer writer;

   List<String> players = new ArrayList<>();
   int[] places = new int[6];
   int[] purses = new int[6];
   boolean[] inPenaltyBox = new boolean[6];

   List<String> popQuestions = new LinkedList<>();
   List<String> scienceQuestions = new LinkedList<>();
   List<String> sportsQuestions = new LinkedList<>();
   List<String> rockQuestions = new LinkedList<>();

   int currentPlayer = 0;
   boolean isGettingOutOfPenaltyBox;

   public GameRefactored(Writer writer) {
      this.writer = writer;
      for (int i = 0; i < 50; i++) {
         popQuestions.add("Pop Question " + i);
         scienceQuestions.add(("Science Question " + i));
         sportsQuestions.add(("Sports Question " + i));
         rockQuestions.add(createRockQuestion(i));
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

   public String createRockQuestion(int index) {
      return "Rock Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   @Override
   public boolean add(String playerName) {


      players.add(playerName);
      places[howManyPlayers()] = 0;
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
      writeText(players.get(currentPlayer) + " is the current player");
      writeText("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
         if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            writeText(players.get(currentPlayer) + " is getting out of the penalty box");
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            writeText(players.get(currentPlayer)
                + "'s new location is "
                + places[currentPlayer]);
            writeText("The category is " + currentCategory());
            askQuestion();
         } else {
            writeText(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
         }

      } else {

         places[currentPlayer] = places[currentPlayer] + roll;
         if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

         writeText(players.get(currentPlayer)
             + "'s new location is "
             + places[currentPlayer]);
         writeText("The category is " + currentCategory());
         askQuestion();
      }

   }

   enum Category {
      Pop, Science, Sports, Rock
   }

   private void askQuestion() {
      String question;
      switch (currentCategory()) {
         case Pop:
            question = popQuestions.remove(0);
            break;
         case Science:
            question = scienceQuestions.remove(0);
            break;
         case Sports:
            question = sportsQuestions.remove(0);
            break;
         case Rock:
            question = rockQuestions.remove(0);
            break;
         default:
            throw new IllegalStateException("Unexpected value: " + currentCategory());
      }
      writeText(question);
   }


   private Category currentCategory() {
      switch (places[currentPlayer]) {
         case 0:
            return Category.Pop;
         case 4:
            return Category.Pop;
         case 8:
            return Category.Pop;

         case 1:
            return Category.Science;
         case 5:
            return Category.Science;
         case 9:
            return Category.Science;
         case 2:
            return Category.Sports;
         case 6:
            return Category.Sports;
         case 10:
            return Category.Sports;
         default:
            return Category.Rock;
      }
   }

   @Override
   public boolean wasCorrectlyAnswered() {
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            writeText("Answer was correct!!!!");
            purses[currentPlayer]++;
            writeText(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
         } else {
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;
            return true;
         }


      } else {

         writeText("Answer was corrent!!!!");
         purses[currentPlayer]++;
         writeText(players.get(currentPlayer)
             + " now has "
             + purses[currentPlayer]
             + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   @Override
   public boolean wrongAnswer() {
      writeText("Question was incorrectly answered");
      writeText(players.get(currentPlayer) + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
