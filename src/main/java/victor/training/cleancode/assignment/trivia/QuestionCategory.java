package victor.training.cleancode.assignment.trivia;

/**
 * Created by Alexandra Petcov on Nov, 2021
 */
enum QuestionCategory {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock"),
    GEOGRAPHY("Geography");

    public final String label;

    QuestionCategory(String label) {
        this.label = label;

    }
}
