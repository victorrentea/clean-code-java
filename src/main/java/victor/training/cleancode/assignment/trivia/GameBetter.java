package victor.training.cleancode.assignment.trivia;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

// REFACTOR ME
public class GameBetter implements IGame {

    private static final Logger log = LogManager.getLogger(GameBetter.class.getName());
    //these fields must be externalized
    private static final int COINS_NUMBER = 6;
    private static final int MIN_NEEDED_STREAKS = 3;
    private static final boolean ENABLE_STREAK = false;
    private static final int MAX_PLACE = 11;
    private static final int DECREASE_PLACE_VALUE = 12;

    final PrepareData prepareData = new PrepareData();
    final Map<QuestionCategory, LinkedList<Question>> questionsMap;
    final Map<Integer, QuestionCategory> places;
    ArrayList<Player> players = new ArrayList<>();

    Player currentPlayer;

    public GameBetter() {
        this.questionsMap = prepareData.createQuestionsMap();
        this.places = prepareData.createPlaces();
    }

    public boolean add(String playerName) {
        final Player newPlayer = new Player(playerName, false, 0, 0, 0);
        players.add(newPlayer);

        //TODO: add log4j config to output to console
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;

    }

    public void roll(int roll) {
        if (currentPlayer == null) {
            // the game just started, the first user is 0
            currentPlayer = players.get(0);
        }

        System.out.println(currentPlayer.getUsername() + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isPenalise()) {
            if (roll % 2 != 0) {
                //the player is getting out of penalty box
                currentPlayer.setPenalise(false);
                System.out.println(currentPlayer.getUsername() + " is getting out of the penalty box");

                calculatePlaceForPlayer(currentPlayer, roll);
                prepareQuestion();
            } else {
                System.out.println(currentPlayer.getUsername() + " is not getting out of the penalty box");

                //TODO: move to the next user -> this one doesnt answer to any question, he is still in prison -> see Readme for output
            }

        } else {
            calculatePlaceForPlayer(currentPlayer, roll);
            prepareQuestion();
        }

    }

    public boolean wasCorrectlyAnswered() {
        boolean winner = true;
        if (!currentPlayer.isPenalise()) {
            winner = correctAnswer();
        }
        getNextPlayer();
        return winner;
    }

    private boolean correctAnswer() {
        currentPlayer.setCorrectAnswers(currentPlayer.getCorrectAnswers() + 1);
        System.out.println("Answer was correct!!!!");
        if (ENABLE_STREAK && currentPlayer.getCorrectAnswers() > MIN_NEEDED_STREAKS) {
            currentPlayer.setCoins(currentPlayer.getCoins() + 2);
        } else {
            currentPlayer.setCoins(currentPlayer.getCoins() + 1);
        }
        System.out.println(currentPlayer.getUsername()
                + " now has "
                + currentPlayer.getCoins()
                + " Gold Coins.");
        return didPlayerWin();
    }

    private void getNextPlayer() {
        //next player
        int currentIndex = players.indexOf(currentPlayer);
        currentIndex++;
        if (currentIndex == players.size()) {
            currentIndex = 0;
        }
        currentPlayer = players.get(currentIndex);
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer.getUsername() + " was sent to the penalty box");
        if (ENABLE_STREAK) {
            //no penalise because of streak
            currentPlayer.setCorrectAnswers(0);
        } else {
            currentPlayer.setPenalise(true);
        }
        getNextPlayer();
        return true;
    }

    public void prepareQuestion() {
        final QuestionCategory currentCategory = getCurrentCategory(currentPlayer.getPlace());
        System.out.println("The category is " + currentCategory.label);
        askQuestion(currentCategory);
    }

    private boolean didPlayerWin() {
        if (ENABLE_STREAK) {
            return currentPlayer.getCoins() != COINS_NUMBER * 2;
        }
        return currentPlayer.getCoins() != COINS_NUMBER;
    }

    private void calculatePlaceForPlayer(Player player, int roll) {
        //set new place for the current player and get rid of magic numbers
        final int newPlace = player.getPlace() + roll;
        if (newPlace > MAX_PLACE) {
            player.setPlace(newPlace - DECREASE_PLACE_VALUE);
        } else {
            player.setPlace(newPlace);
        }
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
        if (places.containsKey(playerPlace)) {
            return places.get(playerPlace);
        }
        return QuestionCategory.ROCK;

    }

    private void askQuestion(QuestionCategory currentCategory) {
        final Question question = questionsMap.get(currentCategory).removeFirst();
        System.out.println(question.getSentence());
    }

}
