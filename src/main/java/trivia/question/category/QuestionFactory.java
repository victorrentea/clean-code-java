package trivia.question.category;

import java.util.HashMap;
import java.util.Map;

public class QuestionFactory {

    private final Map<Integer, Question> placeToCategory = new HashMap<>();
    private Question popQuestionCategory = new Question("Pop");
    private Question scienceQuestionCategory = new Question("Science");
    private Question sportsQuestionCategory = new Question("Sports");
    private Question rockQuestionCategory = new Question("Rock");

    public QuestionFactory() {
        populateMap();
    }

    private void populateMap() {
        // 12 pozitii in total
        placeToCategory.put(0, popQuestionCategory);
        placeToCategory.put(4, popQuestionCategory);
        placeToCategory.put(8, popQuestionCategory);

        placeToCategory.put(1, scienceQuestionCategory);
        placeToCategory.put(5, scienceQuestionCategory);
        placeToCategory.put(9, scienceQuestionCategory);

        placeToCategory.put(2, sportsQuestionCategory);
        placeToCategory.put(6, sportsQuestionCategory);
        placeToCategory.put(10, sportsQuestionCategory);

        placeToCategory.put(3, rockQuestionCategory);
        placeToCategory.put(7, rockQuestionCategory);
        placeToCategory.put(11, rockQuestionCategory);
    }

    public Question currentCategory(int place) { // place e intre [0-11]

        if (place % 4 == 0)
            return popQuestionCategory;
        else if (place % 4 == 1)
            return scienceQuestionCategory;
        else if (place %4 == 2)
            return sportsQuestionCategory;
        else if (place%4 == 3)
            return rockQuestionCategory;
        else throw new IllegalArgumentException("Unde-ai invatat tu matematica ?!");

//        if (placeToCategory.containsKey(place)) {
//            return placeToCategory.get(place);
//        }
//        throw new IllegalArgumentException("Imposibil!");
    }
}
