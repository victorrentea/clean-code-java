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
    @Setter
    private int place;
    @Setter
    private int coins;
    @Setter
    private int correctAnswers;

}
