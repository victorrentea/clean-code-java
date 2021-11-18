package victor.training.cleancode.assignment.trivia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexandra Petcov on Nov, 2021
 */
@AllArgsConstructor
@Getter
public class Player {
    private final String username;
    @Setter
    private boolean penalise;
    private int place;
    @Setter
    private int coins;
    @Setter
    private int correctAnswers;

    // encapsulate the biz rule: "place loops after 12" inside the player, as close as possible to where the state is
    // Uncle Bob will hug you!
    public void advance(int roll) {
        place += roll;
        if (place >= 12) {
            place -= 12;
        }
    }
}
