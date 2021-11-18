package victor.training.cleancode.assignment.trivia;

import lombok.RequiredArgsConstructor;

/**
 * Created by Alexandra Petcov on Nov, 2021
 */
@RequiredArgsConstructor
public class Player {
    private final String username;
    private boolean penalise;
    private int place;
    private int coins;
    private int correctAnswers;

    // encapsulate the biz rule: "place loops after 12" inside the player, as close as possible to where the state is
    // Uncle Bob will hug you!
    public void advance(int roll) {
        place += roll;
        if (place >= 12) {
            place -= 12;
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean isPenalized() {
        return penalise;
    }

    public void moveOutOfPenaltyBox() {
        penalise = false;
    }

    public void moveInPenaltyBox() {
        penalise = true;
    }

    public int getPlace() {
        return place;
    }

    public int getCoins() {
        return coins;
    }

    public Player setCoins(int coins) {
        this.coins = coins;
        return this;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public Player setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
        return this;
    }

    public boolean didPlayerWin() {
        return coins == 6;
    }
}
