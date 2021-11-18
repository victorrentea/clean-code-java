package victor.training.cleancode.assignment.trivia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    public Player setPenalise(boolean penalise) {
        this.penalise = penalise;
        return this;
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
}
