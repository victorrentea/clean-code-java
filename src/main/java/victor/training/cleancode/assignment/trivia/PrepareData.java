package victor.training.cleancode.assignment.trivia;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Alexandra Petcov on Nov, 2021
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PrepareData {

    //Todo: this must be refactored again .. maybe tomorrow, too many things change when new category is added
    public Map<Integer, QuestionCategory> createPlaces() {
        Map<Integer, QuestionCategory> places = new HashMap<>();
        places.put(0, QuestionCategory.POP);
        places.put(4, QuestionCategory.POP);
        places.put(8, QuestionCategory.POP);
        places.put(1, QuestionCategory.SCIENCE);
        places.put(5, QuestionCategory.SCIENCE);
        places.put(9, QuestionCategory.SCIENCE);
        places.put(2, QuestionCategory.SPORTS);
        places.put(6, QuestionCategory.SPORTS);
        places.put(10, QuestionCategory.SPORTS);
        return places;
    }


    public Map<QuestionCategory, LinkedList<Question>> createQuestionsMap() {
        LinkedList<Question> popQuestions = new LinkedList<>();
        LinkedList<Question> scienceQuestions = new LinkedList<>();
        LinkedList<Question> sportsQuestions = new LinkedList<>();
        LinkedList<Question> rockQuestions = new LinkedList<>();
        LinkedList<Question> geographyQuestion = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast(new Question("Pop Question " + i));
            scienceQuestions.addLast(new Question("Science Question " + i));
            sportsQuestions.addLast(new Question("Sports Question " + i));
            rockQuestions.addLast(new Question("Rock Question " + i));
            geographyQuestion.addLast(new Question("Geography Question " + i));
        }
        final EnumMap<QuestionCategory, LinkedList<Question>> questionMap = new EnumMap<>(QuestionCategory.class);
        questionMap.put(QuestionCategory.POP, popQuestions);
        questionMap.put(QuestionCategory.SCIENCE, scienceQuestions);
        questionMap.put(QuestionCategory.SPORTS, sportsQuestions);
        questionMap.put(QuestionCategory.ROCK, rockQuestions);
        questionMap.put(QuestionCategory.GEOGRAPHY, geographyQuestion);


        return questionMap;
    }

}
