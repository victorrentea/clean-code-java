package trivia.question.category;

public class QuestionFactory {

    private final QuestionCategory popQuestion = new QuestionCategory("Pop");
    private final QuestionCategory scienceQuestion = new QuestionCategory("Science");
    private final QuestionCategory sportsQuestion = new QuestionCategory("Sports");
    private final QuestionCategory rockQuestion = new QuestionCategory("Rock");

    public QuestionCategory getQuestionCategory(int place) { // place e intre [0-11]
        switch (place % 4) {
            case 0:
                return popQuestion;
            case 1:
                return scienceQuestion;
            case 2:
                return sportsQuestion;
            case 3:
                return rockQuestion;
        }
        throw new IllegalArgumentException("Unde-ai invatat tu matematica ?!");
    }
}
