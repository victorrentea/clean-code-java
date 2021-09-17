package trivia.question.category.factory;

import trivia.question.category.Question;

import java.util.HashMap;
import java.util.Map;

public class QuestionFactory {

    private Map<Integer, Question> positionToCategoryMap = new HashMap<>();
    private Question popQuestionCategory;
    private Question rockQuestionCategory;
    private Question scienceQuestionCategory;
    private Question sportsQuestionCategory;

    public QuestionFactory() {
        this.popQuestionCategory = new Question("Pop");
        this.rockQuestionCategory = new Question("Rock");
        this.scienceQuestionCategory = new Question("Science");
        this.sportsQuestionCategory = new Question("Sports");
        populateMap();
    }

    private void populateMap() {
        positionToCategoryMap.put(0, popQuestionCategory);
        positionToCategoryMap.put(4, popQuestionCategory);
        positionToCategoryMap.put(8, popQuestionCategory);

        positionToCategoryMap.put(1, scienceQuestionCategory);
        positionToCategoryMap.put(5, scienceQuestionCategory);
        positionToCategoryMap.put(9, scienceQuestionCategory);

        positionToCategoryMap.put(2, sportsQuestionCategory);
        positionToCategoryMap.put(6, sportsQuestionCategory);
        positionToCategoryMap.put(10, sportsQuestionCategory);
    }

    public Question currentCategory(int place) {
        if (positionToCategoryMap.containsKey(place)) {
            return positionToCategoryMap.get(place);
        }
        return rockQuestionCategory;
    }
}
