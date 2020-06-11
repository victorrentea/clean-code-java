package victor.training.trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionSet {
   private final Map<Category, List<String>> questions = new HashMap<>();

  public QuestionSet() {
      for (Category category : Category.values()) {
         questions.put(category, new ArrayList<>());
      }

      for (int i = 0; i < 50; i++) {
         questions.get(Category.POP).add("Pop Question " + i);
         questions.get(Category.SCIENCE).add("Science Question " + i);
         questions.get(Category.SPORTS).add("Sports Question " + i);
         questions.get(Category.ROCK).add("Rock Question " + i);
      }
   }

   public String getNextQuestionFor(Category category) {
      List<String> categoryQuestions = questions.get(category);
      return categoryQuestions.remove(0);
   }
}
