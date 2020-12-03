package victor.training.trivia;

import java.util.ArrayList;
import java.util.List;

class Player {
   private final String name;
   private int place;
   private int purse;
   private boolean inPenaltyBox;

   public Player(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public int getPlace() {
      return place;
   }

   public void advance(int roll) {
      place += roll;
      if (place >= GameBetter.BOARD_SIZE) {
         place -= GameBetter.BOARD_SIZE;
      }
   }
}

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
      purses[players.size()] = 0; // TODO
      inPenaltyBox[players.size()] = false; // TODO

      // TODO e un bug ascuns in cod. Gaseste-l

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
   }

   public void roll(int roll) {
      System.out.println(currentPlayer().getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (inPenaltyBox[currentPlayer]) {
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
      if (inPenaltyBox[currentPlayer]) {
         if (isGettingOutOfPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            purses[currentPlayer]++;
            System.out.println(currentPlayer().getName()
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

         System.out.println("Answer was corrent!!!!");
         purses[currentPlayer]++;
         System.out.println(currentPlayer().getName()
                            + " now has "
                            + purses[currentPlayer]
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getName() + " was sent to the penalty box");
      inPenaltyBox[currentPlayer] = true;

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(purses[currentPlayer] == 6);
   }
}
