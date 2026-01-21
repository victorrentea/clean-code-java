package victor.training.cleancode.trivia;

import java.util.ArrayList;

// REFACTOR ME
public class Game implements IGame {
  ArrayList players = new ArrayList();
  int[] places = new int[6];
  int[] purses = new int[6];
  boolean[] inPenaltyBox = new boolean[6];

  ArrayList popQuestions = new ArrayList();
  ArrayList scienceQuestions = new ArrayList();
  ArrayList sportsQuestions = new ArrayList();
  ArrayList rockQuestions = new ArrayList();

  int cp = 0;
  boolean isGettingOutOfPenaltyBox;

  public Game() {
    for (int i = 0; i < 50; i++) {
      popQuestions.add("Pop Question " + i);
      scienceQuestions.add(("Science Question " + i));
      sportsQuestions.add(("Sports Question " + i));
      rockQuestions.add(createRockQuestion(i));
    }
  }

  public String createRockQuestion(int index) {
    return "Rock Question " + index;
  }

  public boolean isPlayable() {
    return (howManyPlayers() >= 2);
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
    System.out.println(players.get(cp) + " is the current player");
    System.out.println("They have rolled a " + roll);

    if (inPenaltyBox[cp]) {
      if (roll % 2 != 0) {
        isGettingOutOfPenaltyBox = true;

        System.out.println(players.get(cp) + " is getting out of the penalty box");
        places[cp] = places[cp] + roll;
        if (places[cp] > 12) places[cp] = places[cp] - 12;

        System.out.println(players.get(cp)
            + "'s new location is "
            + places[cp]);
        System.out.println("The category is " + currentCategory());
        askQuestion();
      } else {
        System.out.println(players.get(cp) + " is not getting out of the penalty box");
        isGettingOutOfPenaltyBox = false;
      }

    } else {

      places[cp] = places[cp] + roll;
      if (places[cp] > 12) places[cp] = places[cp] - 12;

      System.out.println(players.get(cp)
          + "'s new location is "
          + places[cp]);
      System.out.println("The category is " + currentCategory());
      askQuestion();
    }

  }

  private void askQuestion() {
    if (currentCategory() == "Pop")
      System.out.println(popQuestions.remove(0));
    if (currentCategory() == "Science")
      System.out.println(scienceQuestions.remove(0));
    if (currentCategory() == "Sports")
      System.out.println(sportsQuestions.remove(0));
    if (currentCategory() == "Rock")
      System.out.println(rockQuestions.remove(0));
  }


  private String currentCategory() {
    if (places[cp] - 1 == 0) return "Pop";
    if (places[cp] - 1 == 4) return "Pop";
    if (places[cp] - 1 == 8) return "Pop";
    if (places[cp] - 1 == 1) return "Science";
    if (places[cp] - 1 == 5) return "Science";
    if (places[cp] - 1 == 9) return "Science";
    if (places[cp] - 1 == 2) return "Sports";
    if (places[cp] - 1 == 6) return "Sports";
    if (places[cp] - 1 == 10) return "Sports";
    return "Rock";
  }

  public boolean handleCorrectAnswer() {
    if (inPenaltyBox[cp]) {
      if (isGettingOutOfPenaltyBox) {
        System.out.println("Answer was correct!!!!");
        purses[cp]++;
        System.out.println(players.get(cp)
            + " now has "
            + purses[cp]
            + " Gold Coins.");

        boolean winner = didPlayerWin();
        cp++;
        if (cp == players.size()) cp = 0;

        return winner;
      } else {
        cp++;
        if (cp == players.size()) cp = 0;
        return true;
      }


    } else {

      System.out.println("Answer was corrent!!!!");
      purses[cp]++;
      System.out.println(players.get(cp)
          + " now has "
          + purses[cp]
          + " Gold Coins.");

      boolean winner = didPlayerWin();
      cp++;
      if (cp == players.size()) cp = 0;

      return winner;
    }
  }

  public boolean wrongAnswer() {
    System.out.println("Question was incorrectly answered");
    System.out.println(players.get(cp) + " was sent to the penalty box");
    inPenaltyBox[cp] = true;

    cp++;
    if (cp == players.size()) cp = 0;
    return true;
  }


  private boolean didPlayerWin() {
    return !(purses[cp] == 6);
  }
}
