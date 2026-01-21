package victor.training.cleancode.trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
  public static final int NUMBER_OF_TILES = 12;
  List<Player> players = new ArrayList<>();

  List<String> popQuestions = new ArrayList<>();
  List<String> scienceQuestions = new ArrayList<>();
  List<String> sportsQuestions = new ArrayList<>();
  List<String> rockQuestions = new ArrayList<>();

  int currentPlayer = 0; // TODO victorrentea 2026-01-21: ce-i aia?
  boolean isGettingOutOfPenaltyBox;

  public Game() {
    for (int i = 0; i < 50; i++) {
      popQuestions.add("Pop Question " + i);
      scienceQuestions.add("Science Question " + i);
      sportsQuestions.add("Sports Question " + i);
      rockQuestions.add("Rock Question " + i);
    }
  }

  public boolean add(String playerName) {
    players.add(new Player(playerName));

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    Player player = players.get(currentPlayer);
    System.out.println(player.getName() + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (player.isInPenaltyBox()) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(player.getName() + " is getting out of the penalty box");
        player.movePlayer(roll);

        System.out.println(player.getName() + "'s new location is " + player.getPlace());
        System.out.println("The category is " + categoryName(currentCategory()));
        askQuestion();
      } else {
        System.out.println(player.getName() + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }
    } else {
      player.movePlayer(roll);

      System.out.println(player.getName() + "'s new location is " + player.getPlace());
      System.out.println("The category is " + categoryName(currentCategory()));
      askQuestion();
    }
  }

  private String categoryName(Category category) {
    String lower = category.name().toLowerCase();
    return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
  }

  private void askQuestion() {
    switch (currentCategory()) {
      case POP -> System.out.println(popQuestions.remove(0));
      case SCIENCE -> System.out.println(scienceQuestions.remove(0));
      case SPORTS -> System.out.println(sportsQuestions.remove(0));
      case ROCK -> System.out.println(rockQuestions.remove(0));
    }
  }

  private Category currentCategory() {
    int locationIndex = players.get(currentPlayer).getPlace() - 1;
    return switch (locationIndex % 4) {
      case 0 -> Category.POP;
      case 1 -> Category.SCIENCE;
      case 2 -> Category.SPORTS;
      default -> Category.ROCK;
    };
  }

  public boolean handleCorrectAnswer() {
    Player player = players.get(currentPlayer);
    if (player.isInPenaltyBox()) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");

        player.addCoin();

        System.out.println(player.getName()
            + " now has "
            + player.getCoins()
            + " Gold Coins.");

        boolean winner = !isWinner();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
      } else {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
      }


    } else {

      System.out.println("Answer was correct!!!!");
      player.addCoin();
      System.out.println(player.getName() + " now has " + player.getCoins() + " Gold Coins.");

      boolean winner = !isWinner();
      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;

      return winner;
    }
  }

  public boolean wrongAnswer() {
    Player player = players.get(currentPlayer);
    System.out.println("Question was incorrectly answered");
    System.out.println(player.getName() + " was sent to the penalty box");
    player.setInPenaltyBox(true);

    currentPlayer++;
    if (currentPlayer == players.size()) currentPlayer = 0;
    return true;
  }

  private boolean isWinner() {
    return players.get(currentPlayer).getCoins() == 6;
  }


  enum Category {
    POP, SCIENCE, SPORTS, ROCK
  }
}
