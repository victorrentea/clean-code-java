package victor.training.cleancode.trivia;

import java.util.ArrayList;
import java.util.List;

// REFACTOR ME
public class Game implements IGame {
  List<String> players = new ArrayList<String>();
  int[] places = new int[6];
  int[] purses = new int[6];
  boolean[] inPenaltyBox = new boolean[6];

  List<String> popQuestions = new ArrayList<String>();
  List<String> scienceQuestions = new ArrayList<String>();
  List<String> sportsQuestions = new ArrayList<String>();
  List<String> rockQuestions = new ArrayList<String>();

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
    places[howManyPlayers()] = 1;
    purses[howManyPlayers()] = 0;
    inPenaltyBox[howManyPlayers()] = false;
    players.add(playerName);

    System.out.println(playerName + " was added");
    System.out.println("They are player number " + players.size());
    return true;
  }

  public int howManyPlayers() {
    return players.size();
  }

  public void roll(int roll) {
    System.out.println(players.get(currentPlayer) + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (inPenaltyBox[currentPlayer]) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
        movePlayer(roll);

        System.out.println(players.get(currentPlayer) + "'s new location is " + places[currentPlayer]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
      } else {
        System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }
    } else {
      movePlayer(roll);

      System.out.println(players.get(currentPlayer)
          + "'s new location is "
          + places[currentPlayer]);
      System.out.println("The category is " + currentCategory());
      askQuestion();
    }
  }

  private void movePlayer(int roll) {
    places[currentPlayer] += roll;
    if (places[currentPlayer] > 12) places[currentPlayer] -= 12;
  }

  private void askQuestion() {
    switch (currentCategory()) {
      case POP -> System.out.println(popQuestions.removeFirst());
      case SCIENCE -> System.out.println(scienceQuestions.removeFirst());
      case SPORTS -> System.out.println(sportsQuestions.removeFirst());
      case ROCK -> System.out.println(rockQuestions.removeFirst());
    }
  }

  private Category currentCategory() {
    int locationIndex = places[currentPlayer] - 1;
    return switch (locationIndex % 4) {
      case 0 -> Category.POP;
      case 1 -> Category.SCIENCE;
      case 2 -> Category.SPORTS;
      default -> Category.ROCK;
    };
  }

  public boolean handleCorrectAnswer() {
    if (inPenaltyBox[currentPlayer]) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        purses[currentPlayer]++;
        System.out.println(players.get(currentPlayer)
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
      System.out.println(players.get(currentPlayer)
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
    System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
    inPenaltyBox[currentPlayer] = true;

    currentPlayer++;
    if (currentPlayer == players.size()) currentPlayer = 0;
    return true;
  }

  private boolean didPlayerWin() {
    return !(purses[currentPlayer] == 6);
  }


  enum Category {
    POP, SCIENCE, SPORTS, ROCK
  }
}
