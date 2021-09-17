package trivia.question.category.factory;

import trivia.question.QuestionCategory;
import trivia.question.category.Pop;
import trivia.question.category.Rock;
import trivia.question.category.Science;
import trivia.question.category.Sports;

import java.util.HashMap;
import java.util.Map;

public class Factory {

    private Map<Integer, QuestionCategory> positionToCategoryMap = new HashMap<>();
    private Pop popQuestionCategory;
    private Rock rockQuestionCategory;
    private Science scienceQuestionCategory;
    private Sports sportsQuestionCategory;

    public Factory() {
        this.popQuestionCategory = new Pop();
        this.rockQuestionCategory = new Rock();
        this.scienceQuestionCategory = new Science();
        this.sportsQuestionCategory = new Sports();
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

    public QuestionCategory currentCategory(int place) {
        if (positionToCategoryMap.containsKey(place)) {
            return positionToCategoryMap.get(place);
        }
        return rockQuestionCategory;
    }
}
