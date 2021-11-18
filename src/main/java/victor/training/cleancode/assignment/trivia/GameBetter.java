package victor.training.cleancode.assignment.trivia;

import java.util.ArrayList;
import java.util.List;

public class GameBetter implements IGame {

    private final QuestionRepo questionRepo = new QuestionRepo();
   private final List<Player> players = new ArrayList<>();

   private int currentPlayerIndex;


   public boolean add(String playerName) {
      final Player newPlayer = new Player(playerName);
      players.add(newPlayer);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;

   }

   public void roll(int roll) {
      System.out.println(currentPlayer().getUsername() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (currentPlayer().isPenalized()) {
         if (!isGettingOutOfPenaltyBox(roll)) {
            System.out.println(currentPlayer().getUsername() + " is not getting out of the penalty box");
            return;
         }
         //the player is getting out of penalty box
         currentPlayer().moveOutOfPenaltyBox();
         System.out.println(currentPlayer().getUsername() + " is getting out of the penalty box");
      }
      calculatePlaceForPlayer(currentPlayer(), roll);
      prepareQuestion();
   }

   // http://confluence/jira.intra/....
   private boolean isGettingOutOfPenaltyBox(int roll) {
      return roll % 2 != 0;
   }

   public boolean wasCorrectlyAnswered() {
      boolean winner = true;
      if (!currentPlayer().isPenalized()) {
         winner = correctAnswer();
      }
      getNextPlayer();
      return winner;
   }

   private boolean correctAnswer() {
      currentPlayer().setCorrectAnswers(currentPlayer().getCorrectAnswers() + 1);
      System.out.println("Answer was correct!!!!");
      currentPlayer().setCoins(currentPlayer().getCoins() + 1);
      System.out.println(currentPlayer().getUsername()
                         + " now has "
                         + currentPlayer().getCoins()
                         + " Gold Coins.");
      return !currentPlayer().didPlayerWin();
   }

    private void getNextPlayer() {
      int currentIndex = players.indexOf(currentPlayer());
      currentIndex++;
      if (currentIndex == players.size()) {
         currentIndex = 0;
      }
      currentPlayerIndex = currentIndex;
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(currentPlayer().getUsername() + " was sent to the penalty box");
      currentPlayer().moveInPenaltyBox();
      getNextPlayer();
      return true;
   }

   public void prepareQuestion() {
      final QuestionCategory currentCategory = questionRepo.getCurrentCategory(currentPlayer().getPlace());
      System.out.println("The category is " + currentCategory.label);
      questionRepo.askQuestion(currentCategory);
   }


   private void calculatePlaceForPlayer(Player player, int roll) {
      //set new place for the current player and get rid of magic numbers
      player.advance(roll);
      System.out.println(player.getUsername()
                         + "'s new location is "
                         + player.getPlace());
   }


//    //TODO: Why to call this, if the new minim is 2? - ask this
//    public boolean isPlayable(List<Player> players) {
//        return (howManyPlayers(players) >= 2);
//    }
//
//    public int howManyPlayers(List<Player> players) {
//        return players.size();
//    }

   private QuestionCategory getCurrentCategory(int playerPlace) {

      return questionRepo.getCurrentCategory(playerPlace);
   }

   private void askQuestion(QuestionCategory currentCategory) {
      questionRepo.askQuestion(currentCategory);
   }

   public Player currentPlayer() {
      return players.get(currentPlayerIndex);
   }

}
